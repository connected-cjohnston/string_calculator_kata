defmodule StringCalculator do
  def add("") do
    0
  end

  def add("//" <> string_without_header) do
    [delimiter_group, string] = String.split(string_without_header, "\n")

    string |> add(delimiter_group)
  end

  def add(input) do
    input |> add([",", "\n"])
  end

  defp add(input, delimiters) do
    input
    |> String.split(delimiters)
    |> convert_to_integers()
    |> check_for_negatives()
    |> ignore_big()
    |> sum_values()
  end

  defp convert_to_integers(list) do
    list |> Enum.map(&String.to_integer(&1))
  end

  defp check_for_negatives(list) do
    negatives = list |> Enum.filter(&(&1 < 0))

    if negatives == [] do
      list
    else
      raise "Negatives not allowed: " <> Enum.join(negatives, ", ")
    end
  end

  defp ignore_big(list) do
    list |> Enum.filter(&(&1 <= 1000))
  end

  defp sum_values(list) do
    Enum.reduce(list, 0, fn x, sum ->
      sum + x
    end)
  end
end
