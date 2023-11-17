from gradescope_utils.autograder_utils.decorators import weight, number
from testcasedef import BaseTestCase


class A1TestCases(BaseTestCase):
	def setUp(self):
		super(A1TestCases, self).setUp()
		self.assignment_number = 1
		self.classlist = [
			'Part1',
			'Part2',
			'Part3',
			'Part4',
			'Part5',
			'Part6',
			'Part7',
			'Part8',
			'Part9',
			'Part10'
		]

	@weight(2)
	@number("1.1")
	def test_1_1(self):
		"""
		Part 1 test. Test size = 18. Time limit = 5s.
		:return:
		"""
		val = self.run_test_case('comp2402a1.Part1', infile='infile-1-1.txt', outfile='outfile-1-1.txt')
		self.assertTrue(val, msg=' ')

	@weight(2)
	@number("1.2")
	def test_1_2(self):
		"""
		Part 1 test. Test size = 100. Time limit = 5s.
		:return:
		"""
		val = self.run_test_case('comp2402a1.Part1', infile='infile-1-2.txt', outfile='outfile-1-2.txt')
		self.assertTrue(val, msg=' ')

	@weight(2)
	@number("1.3")
	def test_1_3(self):
		"""
		Part 1 test. Test size = 20000. Time limit = 5s.
		:return:
		"""
		val = self.run_test_case('comp2402a1.Part1', infile='infile-1-3.txt', outfile='outfile-1-3.txt')
		self.assertTrue(val, msg=' ')

	@weight(2)
	@number("1.4")
	def test_1_4(self):
		"""
		Part 1 test. Test size = 100000. Time limit = 5s.
		:return:
		"""
		val = self.run_test_case('comp2402a1.Part1', infile='infile-1-4.txt', outfile='outfile-1-4.txt')
		self.assertTrue(val, msg=' ')

	@weight(2)
	@number("1.5")
	def test_1_5(self):
		"""
		Part 1 test. Test size = 1000000. Time limit = 5s.
		:return:
		"""
		val = self.run_test_case('comp2402a1.Part1', infile='infile-1-5.txt', outfile='outfile-1-5.txt')
		self.assertTrue(val, msg=' ')


	@weight(2)
	@number("2.1")
	def test_2_1(self):
		"""
		Part 2 test. Test size = 19. Time limit = 5s.
		:return:
		"""
		val = self.run_test_case('comp2402a1.Part2', infile='infile-2-1.txt', outfile='outfile-2-1.txt')
		self.assertTrue(val, msg=' ')

	@weight(2)
	@number("2.2")
	def test_2_2(self):
		"""
		Part 2 test. Test size = 100. Time limit = 5s.
		:return:
		"""
		val = self.run_test_case('comp2402a1.Part2', infile='infile-2-2.txt', outfile='outfile-2-2.txt')
		self.assertTrue(val, msg=' ')

	@weight(2)
	@number("2.3")
	def test_2_3(self):
		"""
		Part 2 test. Test size = 20000. Time limit = 5s.
		:return:
		"""
		val = self.run_test_case('comp2402a1.Part2', infile='infile-2-3.txt', outfile='outfile-2-3.txt')
		self.assertTrue(val, msg=' ')

	@weight(2)
	@number("2.4")
	def test_2_4(self):
		"""
		Part 2 test. Test size = 100000. Time limit = 5s.
		:return:
		"""
		val = self.run_test_case('comp2402a1.Part2', infile='infile-2-4.txt', outfile='outfile-2-4.txt')
		self.assertTrue(val, msg=' ')

	@weight(2)
	@number("2.5")
	def test_2_5(self):
		"""
		Part 2 test. Test size = 1000000. Time limit = 5s.
		:return:
		"""
		val = self.run_test_case('comp2402a1.Part2', infile='infile-2-5.txt', outfile='outfile-2-5.txt')
		self.assertTrue(val, msg=' ')


	@weight(2)
	@number("3.1")
	def test_3_1(self):
		"""
		Part 3 test. Test size = 20000. Time limit = 5s. Memory limit = 3m.
		:return:
		"""
		val = self.run_test_case('comp2402a1.Part3', infile='infile-3-3.txt', outfile='outfile-3-3.txt', mem_limit=3)
		self.assertTrue(val, msg=' ')

	@weight(2)
	@number("3.2")
	def test_3_2(self):
		"""
		Part 3 test. Test size = 18. Time limit = 5s. Memory limit = 3m.
		:return:
		"""
		val = self.run_test_case('comp2402a1.Part3', infile='infile-3-1.txt', outfile='outfile-3-1.txt', mem_limit=3)
		self.assertTrue(val, msg=' ')

	@weight(2)
	@number("3.3")
	def test_3_3(self):
		"""
		Part 3 test. Test size = 100. Time limit = 5s. Memory limit = 3m.
		:return:
		"""
		val = self.run_test_case('comp2402a1.Part3', infile='infile-3-2.txt', outfile='outfile-3-2.txt', mem_limit=3)
		self.assertTrue(val, msg=' ')

	@weight(2)
	@number("3.4")
	def test_3_4(self):
		"""
		Part 3 test. Test size = 100000. Time limit = 5s. Memory limit = 3m.
		:return:
		"""
		val = self.run_test_case('comp2402a1.Part3', infile='infile-3-4.txt', outfile='outfile-3-4.txt', mem_limit=3)
		self.assertTrue(val, msg=' ')

	@weight(2)
	@number("3.5")
	def test_3_5(self):
		"""
		Part 3 test. Test size = 1000000. Time limit = 5s. Memory limit = 3m.
		:return:
		"""
		val = self.run_test_case('comp2402a1.Part3', infile='infile-3-5.txt', outfile='outfile-3-5.txt', mem_limit=3)
		self.assertTrue(val, msg=' ')


	@weight(2)
	@number("4.1")
	def test_4_1(self):
		"""
		Part 4 test. Test size = 17. Time limit = 5s.
		:return:
		"""
		val = self.run_test_case('comp2402a1.Part4', infile='infile-4-1.txt', outfile='outfile-4-1.txt')
		self.assertTrue(val, msg=' ')

	@weight(2)
	@number("4.2")
	def test_4_2(self):
		"""
		Part 4 test. Test size = 100. Time limit = 5s.
		:return:
		"""
		val = self.run_test_case('comp2402a1.Part4', infile='infile-4-2.txt', outfile='outfile-4-2.txt')
		self.assertTrue(val, msg=' ')

	@weight(2)
	@number("4.3")
	def test_4_3(self):
		"""
		Part 4 test. Test size = 20000. Time limit = 5s.
		:return:
		"""
		val = self.run_test_case('comp2402a1.Part4', infile='infile-4-3.txt', outfile='outfile-4-3.txt')
		self.assertTrue(val, msg=' ')

	@weight(2)
	@number("4.4")
	def test_4_4(self):
		"""
		Part 4 test. Test size = 100000. Time limit = 5s.
		:return:
		"""
		val = self.run_test_case('comp2402a1.Part4', infile='infile-4-4.txt', outfile='outfile-4-4.txt')
		self.assertTrue(val, msg=' ')

	@weight(2)
	@number("4.5")
	def test_4_5(self):
		"""
		Part 4 test. Test size = 1000000. Time limit = 5s.
		:return:
		"""
		val = self.run_test_case('comp2402a1.Part4', infile='infile-4-5.txt', outfile='outfile-4-5.txt')
		self.assertTrue(val, msg=' ')


	@weight(2)
	@number("5.1")
	def test_5_1(self):
		"""
		Part 5 test. Test size = 19. Time limit = 5s.
		:return:
		"""
		val = self.run_test_case('comp2402a1.Part5', infile='infile-5-1.txt', outfile='outfile-5-1.txt')
		self.assertTrue(val, msg=' ')

	@weight(2)
	@number("5.2")
	def test_5_2(self):
		"""
		Part 5 test. Test size = 100. Time limit = 5s.
		:return:
		"""
		val = self.run_test_case('comp2402a1.Part5', infile='infile-5-2.txt', outfile='outfile-5-2.txt')
		self.assertTrue(val, msg=' ')

	@weight(2)
	@number("5.3")
	def test_5_3(self):
		"""
		Part 5 test. Test size = 20000. Time limit = 5s.
		:return:
		"""
		val = self.run_test_case('comp2402a1.Part5', infile='infile-5-3.txt', outfile='outfile-5-3.txt')
		self.assertTrue(val, msg=' ')

	@weight(2)
	@number("5.4")
	def test_5_4(self):
		"""
		Part 5 test. Test size = 100000. Time limit = 5s.
		:return:
		"""
		val = self.run_test_case('comp2402a1.Part5', infile='infile-5-4.txt', outfile='outfile-5-4.txt')
		self.assertTrue(val, msg=' ')

	@weight(2)
	@number("5.5")
	def test_5_5(self):
		"""
		Part 5 test. Test size = 1000000. Time limit = 5s.
		:return:
		"""
		val = self.run_test_case('comp2402a1.Part5', infile='infile-5-5.txt', outfile='outfile-5-5.txt')
		self.assertTrue(val, msg=' ')


	@weight(2)
	@number("6.1")
	def test_6_1(self):
		"""
		Part 6 test. Test size = 10000. Time limit = 5s. Memory limit = 3m.
		:return:
		"""
		val = self.run_test_case('comp2402a1.Part6', infile='infile-6-3.txt', outfile='outfile-6-3.txt', mem_limit=3)
		self.assertTrue(val, msg=' ')

	@weight(2)
	@number("6.2")
	def test_6_2(self):
		"""
		Part 6 test. Test size = 10. Time limit = 5s. Memory limit = 3m.
		:return:
		"""
		val = self.run_test_case('comp2402a1.Part6', infile='infile-6-1.txt', outfile='outfile-6-1.txt', mem_limit=3)
		self.assertTrue(val, msg=' ')

	@weight(2)
	@number("6.3")
	def test_6_3(self):
		"""
		Part 6 test. Test size = 100. Time limit = 5s. Memory limit = 3m.
		:return:
		"""
		val = self.run_test_case('comp2402a1.Part6', infile='infile-6-2.txt', outfile='outfile-6-2.txt', mem_limit=3)
		self.assertTrue(val, msg=' ')

	@weight(2)
	@number("6.4")
	def test_6_4(self):
		"""
		Part 6 test. Test size = 100000. Time limit = 5s. Memory limit = 3m.
		:return:
		"""
		val = self.run_test_case('comp2402a1.Part6', infile='infile-6-4.txt', outfile='outfile-6-4.txt', mem_limit=3)
		self.assertTrue(val, msg=' ')

	@weight(2)
	@number("6.5")
	def test_6_5(self):
		"""
		Part 6 test. Test size = 1000000. Time limit = 5s. Memory limit = 3m.
		:return:
		"""
		val = self.run_test_case('comp2402a1.Part6', infile='infile-6-5.txt', outfile='outfile-6-5.txt', mem_limit=3)
		self.assertTrue(val, msg=' ')


	@weight(2)
	@number("7.1")
	def test_7_1(self):
		"""
		Part 7 test. Test size = 29. Time limit = 5s.
		:return:
		"""
		val = self.run_test_case('comp2402a1.Part7', infile='infile-7-1.txt', outfile='outfile-7-1.txt')
		self.assertTrue(val, msg=' ')

	@weight(2)
	@number("7.2")
	def test_7_2(self):
		"""
		Part 7 test. Test size = 100. Time limit = 5s.
		:return:
		"""
		val = self.run_test_case('comp2402a1.Part7', infile='infile-7-2.txt', outfile='outfile-7-2.txt')
		self.assertTrue(val, msg=' ')

	@weight(2)
	@number("7.3")
	def test_7_3(self):
		"""
		Part 7 test. Test size = 20000. Time limit = 5s.
		:return:
		"""
		val = self.run_test_case('comp2402a1.Part7', infile='infile-7-3.txt', outfile='outfile-7-3.txt')
		self.assertTrue(val, msg=' ')

	@weight(2)
	@number("7.4")
	def test_7_4(self):
		"""
		Part 7 test. Test size = 100000. Time limit = 5s.
		:return:
		"""
		val = self.run_test_case('comp2402a1.Part7', infile='infile-7-4.txt', outfile='outfile-7-4.txt')
		self.assertTrue(val, msg=' ')

	@weight(2)
	@number("7.5")
	def test_7_5(self):
		"""
		Part 7 test. Test size = 1000000. Time limit = 5s.
		:return:
		"""
		val = self.run_test_case('comp2402a1.Part7', infile='infile-7-5.txt', outfile='outfile-7-5.txt')
		self.assertTrue(val, msg=' ')


	@weight(2)
	@number("8.1")
	def test_8_1(self):
		"""
		Part 8 test. Test size = 15. Time limit = 5s.
		:return:
		"""
		val = self.run_test_case('comp2402a1.Part8', infile='infile-8-1.txt', outfile='outfile-8-1.txt')
		self.assertTrue(val, msg=' ')

	@weight(2)
	@number("8.2")
	def test_8_2(self):
		"""
		Part 8 test. Test size = 100. Time limit = 5s.
		:return:
		"""
		val = self.run_test_case('comp2402a1.Part8', infile='infile-8-2.txt', outfile='outfile-8-2.txt')
		self.assertTrue(val, msg=' ')

	@weight(2)
	@number("8.3")
	def test_8_3(self):
		"""
		Part 8 test. Test size = 10000. Time limit = 5s.
		:return:
		"""
		val = self.run_test_case('comp2402a1.Part8', infile='infile-8-3.txt', outfile='outfile-8-3.txt')
		self.assertTrue(val, msg=' ')

	@weight(2)
	@number("8.4")
	def test_8_4(self):
		"""
		Part 8 test. Test size = 100000. Time limit = 5s.
		:return:
		"""
		val = self.run_test_case('comp2402a1.Part8', infile='infile-8-4.txt', outfile='outfile-8-4.txt')
		self.assertTrue(val, msg=' ')

	@weight(2)
	@number("8.5")
	def test_8_5(self):
		"""
		Part 8 test. Test size = 1000000. Time limit = 5s.
		:return:
		"""
		val = self.run_test_case('comp2402a1.Part8', infile='infile-8-5.txt', outfile='outfile-8-5.txt')
		self.assertTrue(val, msg=' ')


	@weight(2)
	@number("9.1")
	def test_9_1(self):
		"""
		Part 9 test. Test size = 33. Time limit = 5s.
		:return:
		"""
		val = self.run_test_case('comp2402a1.Part9', infile='infile-9-1.txt', outfile='outfile-9-1.txt')
		self.assertTrue(val, msg=' ')

	@weight(2)
	@number("9.2")
	def test_9_2(self):
		"""
		Part 9 test. Test size = 100. Time limit = 5s.
		:return:
		"""
		val = self.run_test_case('comp2402a1.Part9', infile='infile-9-2.txt', outfile='outfile-9-2.txt')
		self.assertTrue(val, msg=' ')

	@weight(2)
	@number("9.3")
	def test_9_3(self):
		"""
		Part 9 test. Test size = 20000. Time limit = 5s.
		:return:
		"""
		val = self.run_test_case('comp2402a1.Part9', infile='infile-9-3.txt', outfile='outfile-9-3.txt')
		self.assertTrue(val, msg=' ')

	@weight(2)
	@number("9.4")
	def test_9_4(self):
		"""
		Part 9 test. Test size = 100000. Time limit = 5s.
		:return:
		"""
		val = self.run_test_case('comp2402a1.Part9', infile='infile-9-4.txt', outfile='outfile-9-4.txt')
		self.assertTrue(val, msg=' ')

	@weight(2)
	@number("9.5")
	def test_9_5(self):
		"""
		Part 9 test. Test size = 1000000. Time limit = 5s.
		:return:
		"""
		val = self.run_test_case('comp2402a1.Part9', infile='infile-9-5.txt', outfile='outfile-9-5.txt')
		self.assertTrue(val, msg=' ')


	@weight(2)
	@number("10.1")
	def test_10_1(self):
		"""
		Part 10 test. Test size = 10. Time limit = 5s.
		:return:
		"""
		val = self.run_test_case('comp2402a1.Part10', infile='infile-10-1.txt', outfile='outfile-10-1.txt')
		self.assertTrue(val, msg=' ')

	@weight(2)
	@number("10.2")
	def test_10_2(self):
		"""
		Part 10 test. Test size = 100. Time limit = 5s.
		:return:
		"""
		val = self.run_test_case('comp2402a1.Part10', infile='infile-10-2.txt', outfile='outfile-10-2.txt')
		self.assertTrue(val, msg=' ')

	@weight(2)
	@number("10.3")
	def test_10_3(self):
		"""
		Part 10 test. Test size = 10000. Time limit = 5s.
		:return:
		"""
		val = self.run_test_case('comp2402a1.Part10', infile='infile-10-3.txt', outfile='outfile-10-3.txt')
		self.assertTrue(val, msg=' ')

	@weight(2)
	@number("10.4")
	def test_10_4(self):
		"""
		Part 10 test. Test size = 100000. Time limit = 5s.
		:return:
		"""
		val = self.run_test_case('comp2402a1.Part10', infile='infile-10-4.txt', outfile='outfile-10-4.txt')
		self.assertTrue(val, msg=' ')

	@weight(2)
	@number("10.5")
	def test_10_5(self):
		"""
		Part 10 test. Test size = 1000000. Time limit = 5s.
		:return:
		"""
		val = self.run_test_case('comp2402a1.Part10', infile='infile-10-5.txt', outfile='outfile-10-5.txt')
		self.assertTrue(val, msg=' ')


class A2TestCases(BaseTestCase):
	def setUp(self):
		super(A2TestCases, self).setUp()
		self.assignment_number = 2
		self.classlist = [
			'SuperStack',
			'SuperSlow',
			'SuperFast',
			'DuperDeque',
			'DuperSlow',
			'DuperFast'
		]

	@weight(9)
	@number("1.1")
	def test_1_1(self):
		"""
		SuperFast operations test. Test size = 10000. Time limit = 5s.
		:return:
		"""
		val = self.run_test_case('SuperOpTester', test_size=10000)
		self.assertTrue(val, msg=' ')

	@weight(9)
	@number("1.2")
	def test_1_2(self):
		"""
		SuperFast operations test. Test size = 50000. Time limit = 5s.
		:return:
		"""
		val = self.run_test_case('SuperOpTester', test_size=50000)
		self.assertTrue(val, msg=' ')

	@weight(9)
	@number("1.3")
	def test_1_3(self):
		"""
		SuperFast operations test. Test size = 100000. Time limit = 5s.
		:return:
		"""
		val = self.run_test_case('SuperOpTester', test_size=100000)
		self.assertTrue(val, msg=' ')

	@weight(9)
	@number("1.4")
	def test_1_4(self):
		"""
		SuperFast operations test. Test size = 250000. Time limit = 5s.
		:return:
		"""
		val = self.run_test_case('SuperOpTester', test_size=250000)
		self.assertTrue(val, msg=' ')

	@weight(9)
	@number("1.5")
	def test_1_5(self):
		"""
		SuperFast operations test. Test size = 500000. Time limit = 5s.
		:return:
		"""
		val = self.run_test_case('SuperOpTester', test_size=500000)
		self.assertTrue(val, msg=' ')

	@weight(5)
	@number("1.6")
	def test_1_6(self):
		"""
		SuperFast iterator test. Test size = 1000. Time limit = 5s.
		:return:
		"""
		val = self.run_test_case('SuperIteratorTester', test_size=1000)
		self.assertTrue(val, msg=' ')

	@weight(9)
	@number("2.1")
	def test_2_1(self):
		"""
		DuperFast operations test. Test size = 10000. Time limit = 5s.
		:return:
		"""
		val = self.run_test_case('DuperOpTester', test_size=10000)
		self.assertTrue(val, msg=' ')

	@weight(9)
	@number("2.2")
	def test_2_2(self):
		"""
		DuperFast operations test. Test size = 50000. Time limit = 5s.
		:return:
		"""
		val = self.run_test_case('DuperOpTester', test_size=50000)
		self.assertTrue(val, msg=' ')

	@weight(9)
	@number("2.3")
	def test_2_3(self):
		"""
		DuperFast operations test. Test size = 100000. Time limit = 5s.
		:return:
		"""
		val = self.run_test_case('DuperOpTester', test_size=100000)
		self.assertTrue(val, msg=' ')

	@weight(9)
	@number("2.4")
	def test_2_4(self):
		"""
		DuperFast operations test. Test size = 250000. Time limit = 5s.
		:return:
		"""
		val = self.run_test_case('DuperOpTester', test_size=250000)
		self.assertTrue(val, msg=' ')

	@weight(9)
	@number("2.5")
	def test_2_5(self):
		"""
		DuperFast operations test. Test size = 500000. Time limit = 5s.
		:return:
		"""
		val = self.run_test_case('DuperOpTester', test_size=500000)
		self.assertTrue(val, msg=' ')

	@weight(5)
	@number("2.6")
	def test_2_6(self):
		"""
		DuperFast iterator test. Test size = 1000. Time limit = 5s.
		:return:
		"""
		val = self.run_test_case('DuperIteratorTester', test_size=1000)
		self.assertTrue(val, msg=' ')


class A3TestCases(BaseTestCase):
	def setUp(self):
		super(A3TestCases, self).setUp()
		self.assignment_number = 3
		self.classlist = [
			'DefaultComparator',
			'SSet',
			'IndexedSSet',
			'SkippitySlow',
			'SkippityFast',
			'BinaryTree'
		]

	@weight(10)
	@number("1.1")
	def test_1_1(self):
		"""
		SkippityFast operations test. Test size = 25000. Time limit = 5s. Stack limit = 150k.
		:return:
		"""
		val = self.run_test_case('Part1Tester', test_size=25000, stk_limit=150)
		self.assertTrue(val, msg=' ')

	@weight(10)
	@number("1.2")
	def test_1_2(self):
		"""
		SkippityFast operations test. Test size = 50000. Time limit = 5s. Stack limit = 150k.
		:return:
		"""
		val = self.run_test_case('Part1Tester', test_size=50000, stk_limit=150)
		self.assertTrue(val, msg=' ')

	@weight(10)
	@number("1.3")
	def test_1_3(self):
		"""
		SkippityFast operations test. Test size = 100000. Time limit = 5s. Stack limit = 150k.
		:return:
		"""
		val = self.run_test_case('Part1Tester', test_size=100000, stk_limit=150)
		self.assertTrue(val, msg=' ')

	@weight(10)
	@number("1.4")
	def test_1_4(self):
		"""
		SkippityFast operations test. Test size = 250000. Time limit = 5s. Stack limit = 150k.
		:return:
		"""
		val = self.run_test_case('Part1Tester', test_size=250000, stk_limit=150)
		self.assertTrue(val, msg=' ')

	@weight(3)
	@number("2.1.1")
	def test_2_1_1(self):
		"""
		leafAndOnlyLeaf() test. Test size = 0. Time limit = 5s. Stack limit = 150k.
		:return:
		"""
		val = self.run_test_case('Part21Tester', test_size=0, stk_limit=150)
		self.assertTrue(val, msg=' ')

	@weight(3)
	@number("2.1.2")
	def test_2_1_2(self):
		"""
		leafAndOnlyLeaf() test. Test size = 80. Time limit = 5s. Stack limit = 150k.
		:return:
		"""
		val = self.run_test_case('Part21Tester', test_size=80, stk_limit=150)
		self.assertTrue(val, msg=' ')

	@weight(3)
	@number("2.1.3")
	def test_2_1_3(self):
		"""
		leafAndOnlyLeaf() test. Test size = 50000. Time limit = 5s. Stack limit = 150k.
		:return:
		"""
		val = self.run_test_case('Part21Tester', test_size=50000, stk_limit=150)
		self.assertTrue(val, msg=' ')

	@weight(3)
	@number("2.1.4")
	def test_2_1_4(self):
		"""
		leafAndOnlyLeaf() test. Test size = 100000. Time limit = 5s. Stack limit = 150k.
		:return:
		"""
		val = self.run_test_case('Part21Tester', test_size=100000, stk_limit=150)
		self.assertTrue(val, msg=' ')

	@weight(3)
	@number("2.1.5")
	def test_2_1_5(self):
		"""
		leafAndOnlyLeaf() test. Test size = 1000000. Time limit = 5s. Stack limit = 150k.
		:return:
		"""
		val = self.run_test_case('Part21Tester', test_size=1000000, stk_limit=150)
		self.assertTrue(val, msg=' ')

	@weight(3)
	@number("2.2.1")
	def test_2_2_1(self):
		"""
		dawnOfSpring() test. Test size = 0. Time limit = 5s. Stack limit = 150k.
		:return:
		"""
		val = self.run_test_case('Part22Tester', test_size=0, stk_limit=150)
		self.assertTrue(val, msg=' ')

	@weight(3)
	@number("2.2.2")
	def test_2_2_2(self):
		"""
		dawnOfSpring() test. Test size = 80. Time limit = 5s. Stack limit = 150k.
		:return:
		"""
		val = self.run_test_case('Part22Tester', test_size=80, stk_limit=150)
		self.assertTrue(val, msg=' ')

	@weight(3)
	@number("2.2.3")
	def test_2_2_3(self):
		"""
		dawnOfSpring() test. Test size = 50000. Time limit = 5s. Stack limit = 150k.
		:return:
		"""
		val = self.run_test_case('Part22Tester', test_size=50000, stk_limit=150)
		self.assertTrue(val, msg=' ')

	@weight(3)
	@number("2.2.4")
	def test_2_2_4(self):
		"""
		dawnOfSpring() test. Test size = 100000. Time limit = 5s. Stack limit = 150k.
		:return:
		"""
		val = self.run_test_case('Part22Tester', test_size=100000, stk_limit=150)
		self.assertTrue(val, msg=' ')

	@weight(3)
	@number("2.2.5")
	def test_2_2_5(self):
		"""
		dawnOfSpring() test. Test size = 1000000. Time limit = 5s. Stack limit = 150k.
		:return:
		"""
		val = self.run_test_case('Part22Tester', test_size=1000000, stk_limit=150)
		self.assertTrue(val, msg=' ')

	@weight(3)
	@number("2.3.1")
	def test_2_3_1(self):
		"""
		monkeyLand() test. Test size = 0. Time limit = 5s. Stack limit = 150k.
		:return:
		"""
		val = self.run_test_case('Part23Tester', test_size=0, stk_limit=150)
		self.assertTrue(val, msg=' ')

	@weight(3)
	@number("2.3.2")
	def test_2_3_2(self):
		"""
		monkeyLand() test. Test size = 80. Time limit = 5s. Stack limit = 150k.
		:return:
		"""
		val = self.run_test_case('Part23Tester', test_size=80, stk_limit=150)
		self.assertTrue(val, msg=' ')

	@weight(3)
	@number("2.3.3")
	def test_2_3_3(self):
		"""
		monkeyLand() test. Test size = 50000. Time limit = 5s. Stack limit = 150k.
		:return:
		"""
		val = self.run_test_case('Part23Tester', test_size=50000, stk_limit=150)
		self.assertTrue(val, msg=' ')

	@weight(3)
	@number("2.3.4")
	def test_2_3_4(self):
		"""
		monkeyLand() test. Test size = 100000. Time limit = 5s. Stack limit = 150k.
		:return:
		"""
		val = self.run_test_case('Part23Tester', test_size=100000, stk_limit=150)
		self.assertTrue(val, msg=' ')

	@weight(3)
	@number("2.3.5")
	def test_2_3_5(self):
		"""
		monkeyLand() test. Test size = 1000000. Time limit = 5s. Stack limit = 150k.
		:return:
		"""
		val = self.run_test_case('Part23Tester', test_size=1000000, stk_limit=150)
		self.assertTrue(val, msg=' ')

	@weight(3)
	@number("2.4.1")
	def test_2_4_1(self):
		"""
		bracketSequence() test. Test size = 0. Time limit = 5s. Stack limit = 150k.
		:return:
		"""
		val = self.run_test_case('Part24Tester', test_size=0, stk_limit=150)
		self.assertTrue(val, msg=' ')

	@weight(3)
	@number("2.4.2")
	def test_2_4_2(self):
		"""
		bracketSequence() test. Test size = 80. Time limit = 5s. Stack limit = 150k.
		:return:
		"""
		val = self.run_test_case('Part24Tester', test_size=80, stk_limit=150)
		self.assertTrue(val, msg=' ')

	@weight(3)
	@number("2.4.3")
	def test_2_4_3(self):
		"""
		bracketSequence() test. Test size = 50000. Time limit = 5s. Stack limit = 150k.
		:return:
		"""
		val = self.run_test_case('Part24Tester', test_size=50000, stk_limit=150)
		self.assertTrue(val, msg=' ')

	@weight(3)
	@number("2.4.4")
	def test_2_4_4(self):
		"""
		bracketSequence() test. Test size = 100000. Time limit = 5s. Stack limit = 150k.
		:return:
		"""
		val = self.run_test_case('Part24Tester', test_size=100000, stk_limit=150)
		self.assertTrue(val, msg=' ')

	@weight(3)
	@number("2.4.5")
	def test_2_4_5(self):
		"""
		bracketSequence() test. Test size = 500000. Time limit = 5s. Stack limit = 150k.
		:return:
		"""
		val = self.run_test_case('Part24Tester', test_size=500000, stk_limit=150)
		self.assertTrue(val, msg=' ')


class A4TestCases(BaseTestCase):
	def setUp(self):
		super(A4TestCases, self).setUp()
		self.assignment_number = 4
		self.classlist = [
			'UltraStack',
			'UltraSlow',
			'UltraFast'
		]

	@weight(18)
	@number("1.1")
	def test_1_1(self):
		"""
		UltraFast operations test. Test size = 50000. Time limit = 5s. Memory limit = 10m. Stack limit = 150k.
		:return:
		"""
		val = self.run_test_case('UltraOpTester', test_size=50000, mem_limit=10, stk_limit=150)
		self.assertTrue(val, msg=' ')

	@weight(18)
	@number("1.2")
	def test_1_2(self):
		"""
		UltraFast operations test. Test size = 100000. Time limit = 5s. Memory limit = 16m. Stack limit = 150k.
		:return:
		"""
		val = self.run_test_case('UltraOpTester', test_size=100000, mem_limit=16, stk_limit=150)
		self.assertTrue(val, msg=' ')

	@weight(18)
	@number("1.3")
	def test_1_3(self):
		"""
		UltraFast operations test. Test size = 250000. Time limit = 5s. Memory limit = 34m. Stack limit = 150k.
		:return:
		"""
		val = self.run_test_case('UltraOpTester', test_size=250000, mem_limit=34, stk_limit=150)
		self.assertTrue(val, msg=' ')

	@weight(18)
	@number("1.4")
	def test_1_4(self):
		"""
		UltraFast operations test. Test size = 500000. Time limit = 5s. Memory limit = 64m. Stack limit = 150k.
		:return:
		"""
		val = self.run_test_case('UltraOpTester', test_size=500000, mem_limit=64, stk_limit=150)
		self.assertTrue(val, msg=' ')

	@weight(18)
	@number("1.5")
	def test_1_5(self):
		"""
		UltraFast operations test. Test size = 1000000. Time limit = 5s. Memory limit = 124m. Stack limit = 150k.
		:return:
		"""
		val = self.run_test_case('UltraOpTester', test_size=1000000, mem_limit=124, stk_limit=150)
		self.assertTrue(val, msg=' ')

	@weight(10)
	@number("1.6")
	def test_1_6(self):
		"""
		UltraFast iterator test. Test size = 1000. Time limit = 5s. Memory limit = 10m. Stack limit = 150k.
		:return:
		"""
		val = self.run_test_case('UltraIteratorTester', test_size=1000, mem_limit=10, stk_limit=150)
		self.assertTrue(val, msg=' ')


class A5TestCases(BaseTestCase):
	def setUp(self):
		super(A5TestCases, self).setUp()
		self.assignment_number = 5
		self.classlist = [
			'MixAndBoom',
			'SnakesAndLadders'
		]

	@weight(5)
	@number("1.1")
	def test_1_1(self):
		"""
		MixAndBoom test. Test size = 20. Time limit = 5s. Memory limit = 160m.
		:return:
		"""
		val = self.run_test_case('comp2402a5.MixAndBoom', infile='infile-1-1.txt', outfile='outfile-1-1.txt', mem_limit=160)
		self.assertTrue(val, msg=' ')

	@weight(5)
	@number("1.2")
	def test_1_2(self):
		"""
		MixAndBoom test. Test size = 100. Time limit = 5s. Memory limit = 160m.
		:return:
		"""
		val = self.run_test_case('comp2402a5.MixAndBoom', infile='infile-1-2.txt', outfile='outfile-1-2.txt', mem_limit=160)
		self.assertTrue(val, msg=' ')

	@weight(5)
	@number("1.3")
	def test_1_3(self):
		"""
		MixAndBoom test. Test size = 1000. Time limit = 5s. Memory limit = 160m.
		:return:
		"""
		val = self.run_test_case('comp2402a5.MixAndBoom', infile='infile-1-3.txt', outfile='outfile-1-3.txt', mem_limit=160)
		self.assertTrue(val, msg=' ')

	@weight(5)
	@number("1.4")
	def test_1_4(self):
		"""
		MixAndBoom test. Test size = 10000. Time limit = 5s. Memory limit = 160m.
		:return:
		"""
		val = self.run_test_case('comp2402a5.MixAndBoom', infile='infile-1-4.txt', outfile='outfile-1-4.txt', mem_limit=160)
		self.assertTrue(val, msg=' ')

	@weight(5)
	@number("1.5")
	def test_1_5(self):
		"""
		MixAndBoom test. Test size = 50000. Time limit = 5s. Memory limit = 160m.
		:return:
		"""
		val = self.run_test_case('comp2402a5.MixAndBoom', infile='infile-1-5.txt', outfile='outfile-1-5.txt', mem_limit=160)
		self.assertTrue(val, msg=' ')

	@weight(5)
	@number("1.6")
	def test_1_6(self):
		"""
		MixAndBoom test. Test size = 100000. Time limit = 5s. Memory limit = 160m.
		:return:
		"""
		val = self.run_test_case('comp2402a5.MixAndBoom', infile='infile-1-6.txt', outfile='outfile-1-6.txt', mem_limit=160)
		self.assertTrue(val, msg=' ')

	@weight(5)
	@number("1.7")
	def test_1_7(self):
		"""
		MixAndBoom test. Test size = 500000. Time limit = 5s. Memory limit = 160m.
		:return:
		"""
		val = self.run_test_case('comp2402a5.MixAndBoom', infile='infile-1-7.txt', outfile='outfile-1-7.txt', mem_limit=160)
		self.assertTrue(val, msg=' ')

	@weight(5)
	@number("1.8")
	def test_1_8(self):
		"""
		MixAndBoom test. Test size = 1000000. Time limit = 5s. Memory limit = 160m.
		:return:
		"""
		val = self.run_test_case('comp2402a5.MixAndBoom', infile='infile-1-8.txt', outfile='outfile-1-8.txt', mem_limit=160)
		self.assertTrue(val, msg=' ')

	@weight(6)
	@number("2.1")
	def test_2_1(self):
		"""
		SnakesAndLadders test. Test size = 20. Time limit = 5s. Memory limit = 70m.
		:return:
		"""
		val = self.run_test_case('comp2402a5.SnakesAndLadders', infile='infile-2-1.txt', outfile='outfile-2-1.txt', mem_limit=70)
		self.assertTrue(val, msg=' ')

	@weight(6)
	@number("2.2")
	def test_2_2(self):
		"""
		SnakesAndLadders test. Test size = 20. Time limit = 5s. Memory limit = 70m.
		:return:
		"""
		val = self.run_test_case('comp2402a5.SnakesAndLadders', infile='infile-2-2.txt', outfile='outfile-2-2.txt', mem_limit=70)
		self.assertTrue(val, msg=' ')

	@weight(6)
	@number("2.3")
	def test_2_3(self):
		"""
		SnakesAndLadders test. Test size = 100. Time limit = 5s. Memory limit = 70m.
		:return:
		"""
		val = self.run_test_case('comp2402a5.SnakesAndLadders', infile='infile-2-3.txt', outfile='outfile-2-3.txt', mem_limit=70)
		self.assertTrue(val, msg=' ')

	@weight(6)
	@number("2.4")
	def test_2_4(self):
		"""
		SnakesAndLadders test. Test size = 500. Time limit = 5s. Memory limit = 70m.
		:return:
		"""
		val = self.run_test_case('comp2402a5.SnakesAndLadders', infile='infile-2-4.txt', outfile='outfile-2-4.txt', mem_limit=70)
		self.assertTrue(val, msg=' ')

	@weight(8)
	@number("2.5")
	def test_2_5(self):
		"""
		SnakesAndLadders test. Test size = 800. Time limit = 5s. Memory limit = 70m.
		:return:
		"""
		val = self.run_test_case('comp2402a5.SnakesAndLadders', infile='infile-2-5.txt', outfile='outfile-2-5.txt', mem_limit=70)
		self.assertTrue(val, msg=' ')

	@weight(8)
	@number("2.6")
	def test_2_6(self):
		"""
		SnakesAndLadders test. Test size = 1000. Time limit = 5s. Memory limit = 70m.
		:return:
		"""
		val = self.run_test_case('comp2402a5.SnakesAndLadders', infile='infile-2-6.txt', outfile='outfile-2-6.txt', mem_limit=70)
		self.assertTrue(val, msg=' ')

	@weight(10)
	@number("2.7")
	def test_2_7(self):
		"""
		SnakesAndLadders test. Test size = 1200. Time limit = 5s. Memory limit = 70m.
		:return:
		"""
		val = self.run_test_case('comp2402a5.SnakesAndLadders', infile='infile-2-7.txt', outfile='outfile-2-7.txt', mem_limit=70)
		self.assertTrue(val, msg=' ')

	@weight(10)
	@number("2.8")
	def test_2_8(self):
		"""
		SnakesAndLadders test. Test size = 1500. Time limit = 5s. Memory limit = 70m.
		:return:
		"""
		val = self.run_test_case('comp2402a5.SnakesAndLadders', infile='infile-2-8.txt', outfile='outfile-2-8.txt', mem_limit=70)
		self.assertTrue(val, msg=' ')
