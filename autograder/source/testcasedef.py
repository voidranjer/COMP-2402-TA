import os
import time
import shutil
import unittest
import subprocess
from enum import Enum
from gradescope_utils.autograder_utils.decorators import weight, number


class ERR_CODE(Enum):
    BADUPLOAD = 0
    BADCOMPILE = 1
    CLASSNOTFOUND = 2
    WRONGOUTPUT = 3
    TIMEOUTEXPIRED = 4
    RUNTIMEEXCEPTION = 5
    BADFILEIO = 6
    EARLYEXIT = 7
    EXECTIME = 8


class BaseTestCase(unittest.TestCase):
    def setUp(self):
        self.assignment_number = 0
        self.classlist = []
        self.unpack_dir = 'testbed'
        self.longMessage = False
        self.msg_table = {
            ERR_CODE.BADUPLOAD: 'Could not find file {}. Aborting. Be sure to upload all required files.',
            ERR_CODE.BADCOMPILE: 'Error compiling java file {}. Aborting.',
            ERR_CODE.CLASSNOTFOUND: 'Class file not found.',
            ERR_CODE.WRONGOUTPUT: 'Output is incorrect.\nLine {}, correct != result [\'{}\'!=\'{}\']',
            ERR_CODE.TIMEOUTEXPIRED: 'Program timed out.',
            ERR_CODE.RUNTIMEEXCEPTION: 'Program terminated (possibly out of memory).',
            ERR_CODE.BADFILEIO: 'An exception occurred while opening input/output file(s).',
            ERR_CODE.EARLYEXIT: 'Program exited before test finished.',
            ERR_CODE.EXECTIME: 'Execution time: {:.2f} seconds.'
        }

    def print_filtered_stderr(self, err):
        if err is not None:
            lines = [x[9:] for x in err.decode('utf-8').split('\n') if x.startswith('MAGICODE:')]
            if len(lines) == 0:
                return False
            if lines[-1] == 'NoMonkeyBusiness':
                print('\n'.join(lines[:-1]))
                return True
            print('\n'.join(lines))
            return False

    def run_with_timeout(self, command, timeout, check_validity=True):
        try:
            sp = subprocess.run(command, capture_output=True, timeout=timeout)
            spcl_msg = self.print_filtered_stderr(sp.stderr)
            if check_validity:
                if spcl_msg:
                    return 0
                else:
                    print(self.msg_table[ERR_CODE.EARLYEXIT])
                    return -1
            return 0
        except subprocess.TimeoutExpired as ex:
            self.print_filtered_stderr(ex.stderr)
            print(self.msg_table[ERR_CODE.TIMEOUTEXPIRED])
            return -1
        except:
            print(self.msg_table[ERR_CODE.RUNTIMEEXCEPTION])
            return -1

    def compare_files(self, correct, generated):
        line_no = 0
        try:
            with open(correct) as fa:
                with open(generated) as fb:
                    linea = fa.readline()
                    lineb = fb.readline()
                    while linea or lineb:
                        if linea != lineb:
                            linea, lineb = linea.strip(), lineb.strip()
                            if len(linea) < len(lineb):
                                lineb = lineb[:max(2, len(linea))] + '...'
                            print(self.msg_table[ERR_CODE.WRONGOUTPUT].format(line_no, linea, lineb))
                            return False
                        linea = fa.readline()
                        lineb = fb.readline()
                        line_no += 1
        except:
            print(self.msg_table[ERR_CODE.BADFILEIO])
            return False
        return True

    def run_test_case(self,
                      classname,
                      infile=None,
                      outfile=None,
                      test_size=None,
                      time_limit=5,
                      mem_limit=None,
                      stk_limit=None):
        if not os.path.exists(os.path.join(self.unpack_dir, os.sep.join(classname.split('.')) + '.class')):
            print(self.msg_table[ERR_CODE.CLASSNOTFOUND])
            return False
        cwd = os.getcwd()
        os.chdir(self.unpack_dir)
        cmd = ['java']
        if mem_limit is not None:
            cmd.extend(['-Xmx{}m'.format(mem_limit)])
        if stk_limit is not None:
            cmd.extend(['-Xss{}k'.format(stk_limit)])
        cmd.extend(['-classpath', '.:../assets/pro-grade.jar',
                    '-Djava.security.manager=net.sourceforge.prograde.sm.ProGradeJSM'])
        if infile is not None:
            infile = os.path.join('..', 'assets', str(self.assignment_number), infile)
            outfile = os.path.join('..', 'assets', str(self.assignment_number), outfile)
            cmd.extend(['-Djava.security.policy==../assets/a1.policy'])
            cmd.extend(['-Dinfile=' + infile, '-Doutfile=out.txt', classname, infile, 'out.txt'])
        else:
            cmd.extend(['-Djava.security.policy==../assets/strict.policy'])
            cmd.extend([classname, str(test_size)])

        starttime = time.time()
        retcode = self.run_with_timeout(cmd, time_limit, check_validity=(infile is None))
        endtime = time.time()
        if infile is not None:
            ret_val = (retcode == 0 and self.compare_files(outfile, 'out.txt'))
            os.remove('out.txt')
        else:
            ret_val = (retcode == 0)
        print(self.msg_table[ERR_CODE.EXECTIME].format(endtime - starttime))
        os.chdir(cwd)
        return ret_val

    def unpack_n_compile(self):
        a_name = 'comp2402a{}'.format(self.assignment_number)
        if os.path.exists(self.unpack_dir):
            shutil.rmtree(self.unpack_dir)
        os.mkdir(self.unpack_dir)
        os.mkdir(os.path.join(self.unpack_dir, a_name))
        for javafile in [x for x in os.listdir('submission') if x.endswith('.java')]:
            shutil.copy(os.path.join('submission', javafile), os.path.join(self.unpack_dir, a_name, javafile))
        for classname in self.classlist:
            filename = classname + '.java'
            if not os.path.exists(os.path.join(self.unpack_dir, a_name, filename)):
                print(self.msg_table[ERR_CODE.BADUPLOAD].format(filename))
                return False
        cwd = os.getcwd()
        os.chdir(self.unpack_dir)

        for f in os.listdir(os.path.join('..', 'assets', str(self.assignment_number))):
            if f.endswith('.java'):
                shutil.copy(os.path.join('..', 'assets', str(self.assignment_number), f), '.')
        javafiles = [os.path.join(a_name, f) for f in os.listdir(a_name) if f.endswith('.java')]
        javafiles.extend([f for f in os.listdir('.') if f.endswith('.java')])
        for f in javafiles:
            cmd = ['javac', '-encoding', 'utf8', f]
            try:
                res = subprocess.check_output(cmd, stderr=subprocess.STDOUT)
            except subprocess.CalledProcessError as e:
                if f.startswith(a_name):
                    print(self.msg_table[ERR_CODE.BADCOMPILE].format(f))
                    print(e.output.decode('utf-8'))
                else:
                    print(self.msg_table[ERR_CODE.BADCOMPILE].format('(server side)'))
                classfiles = [''.join(x.split('.')[:-1])+'.class' for x in javafiles]
                for x in classfiles:
                    if os.path.exists(x):
                        os.remove(x)
                os.chdir(cwd)
                return False
        os.chdir(cwd)
        return True

    @weight(0)
    @number("0")
    def test_0(self):
        """
        Test to ensure all required files are submitted and compile without issue.
        :return:
        """
        val = self.unpack_n_compile()
        self.assertTrue(val, msg=' ')


