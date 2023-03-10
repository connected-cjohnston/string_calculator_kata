defmodule StringCalculatorTest do
  # 1. An empty string returns zero `'' => 0`
  # 2. A single number returns the value `'1' => 1` `'2' => 2`
  # 3. Two numbers, comma delimited, returns the sum `'1,2' => 3` `'10,20' => 30`
  # 4. Two numbers, newline delimited, returns the sum `'1\n2' => 3`
  # 5. Three numbers, delimited either way, returns the sum `'1\n2,3\n4' => 10`
  # 6. Negative numbers throw an exception with the message `'-1,2,-3' => 'negatives not allowed: -1,-3'`
  # 7. Numbers greater than 1000 are ignored
  # 8. A single char delimiter can be defined on the first line starting with `//` (e.g `//#\n1#2` for a ‘#’ as the delimiter)
  # 9. A multi char delimiter can be defined on the first line starting with `//` (e.g. `//###\n1###2` for ‘###’ as the delimiter)

  use ExUnit.Case

  test "should return 0 when given an empty string" do
    assert StringCalculator.add("") == 0
  end

  test "should return the input as an integer when it contains 1 integer" do
    assert StringCalculator.add("5") == 5
  end

  test "should return 3 when given 1,2" do
    assert StringCalculator.add("1,2") == 3
  end

  test "should return 3 when given 1\n2" do
    assert StringCalculator.add("1\n2") == 3
  end

  test "should sum up 4 numbers" do
    assert StringCalculator.add("1\n2,3\n4") == 10
  end

  test "should throw an exception when string contains a negative number" do
    assert_raise RuntimeError, "Negatives not allowed: -2, -3", fn ->
      StringCalculator.add("1,-2,-3")
    end
  end

  test "should ignore numbers larger than 1000" do
    assert StringCalculator.add("1,2,1001") == 3
  end

  test "should work with single character custom delimiter" do
    assert StringCalculator.add("//&\n1&2&3") == 6
  end

  test "should work with multiple character custom delimiter" do
    assert StringCalculator.add("//***\n1***2***3")
  end
end
