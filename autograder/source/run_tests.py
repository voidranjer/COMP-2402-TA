import os
import io
import json
import shutil
import unittest
from gradescope_utils.autograder_utils.json_test_runner import JSONTestRunner
import testcases


def get_a1_suites():
    test_names = (
            [['test_0']] +
            [['test_{}_{}'.format(i, j) for j in range(1, 6)] for i in range(1, 11)]
    )
    suites = [unittest.TestLoader().loadTestsFromNames(x, testcases.A1TestCases) for x in test_names]
    return suites


def get_a2_suites():
    test_names = (
            [['test_0']] +
            [['test_1_{}'.format(i) for i in range(1, 6)]] +
            [['test_1_6']] +
            [['test_2_{}'.format(i) for i in range(1, 6)]] +
            [['test_2_6']]
    )
    suites = [unittest.TestLoader().loadTestsFromNames(x, testcases.A2TestCases) for x in test_names]
    return suites


def get_a3_suites():
    test_names = (
            [['test_0']] +
            [['test_1_{}'.format(i) for i in range(1, 5)]] +
            [['test_2_{}_{}'.format(i, j) for j in range(1, 6)] for i in range(1, 5)]
    )
    suites = [unittest.TestLoader().loadTestsFromNames(x, testcases.A3TestCases) for x in test_names]
    return suites


def get_a4_suites():
    test_names = (
            [['test_0']] +
            [['test_1_{}'.format(i) for i in range(1, 6)]] +
            [['test_1_6']]
    )
    suites = [unittest.TestLoader().loadTestsFromNames(x, testcases.A4TestCases) for x in test_names]
    return suites


def get_a5_suites():
    test_names = (
            [['test_0']] +
            [['test_1_{}'.format(i) for i in range(1, 9)]] +
            [['test_2_{}'.format(i) for i in range(1, 9)]]
    )
    suites = [unittest.TestLoader().loadTestsFromNames(x, testcases.A5TestCases) for x in test_names]
    return suites


if __name__ == '__main__':
    result_dir = 'results'
    if os.path.exists(result_dir):
        shutil.rmtree(result_dir)
    os.mkdir(result_dir)
    suites = get_a4_suites()

    all_tests = []
    exec_time = 0.0
    score = 0
    final_json = None
    for suite in suites:
        with io.StringIO() as f:
            JSONTestRunner(visibility='visible', stream=f, failure_prefix='', failfast=True).run(suite)
            f.seek(0)
            jsondata = json.load(f)
            for test in jsondata['tests']:
                all_tests.append(test)
                score += test['score']
            exec_time += float(jsondata['execution_time'])
            final_json = jsondata
    final_json['tests'] = all_tests
    final_json['execution_time'] = format(exec_time, "0.2f")
    final_json['score'] = score
    with open(os.path.join(result_dir, 'results.json'), 'w') as f:
        json.dump(final_json, f, indent=4)
        f.write('\n')
