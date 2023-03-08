defmodule StringCalculator do
  def add(input) when input == "", do: 0

  def add(input) do
    if String.contains?(input, ",") or String.contains?(input, "\n") do
      foo = String.replace(input, "\n", ",", global: true)
      integers = String.split(foo, ",")
      String.to_integer(Enum.at(integers, 0)) + String.to_integer(Enum.at(integers, 1))
    else
      String.to_integer(input)
    end
  end
end
