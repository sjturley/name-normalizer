defmodule NameNormalizer do
  def normalize(name) do
    name |> String.split(", ") |> normalize_name_and_maybe_suffix()
  end

  defp normalize_name_and_maybe_suffix([name, suffix]) do
    with {:ok, normalized_name} <- normalize_name_and_maybe_suffix([name]) do
      {:ok, "#{normalized_name}, #{suffix}"}
    end
  end

  defp normalize_name_and_maybe_suffix([name]) do
    {:ok, name |> String.split() |> handle_parts()}
  end

  defp normalize_name_and_maybe_suffix(_) do
    {:error, "Too many commas"}
  end

  defp handle_parts([]), do: ""
  defp handle_parts([single_name]), do: single_name
  defp handle_parts([first_name, last_name]), do: "#{last_name}, #{first_name}"

  defp handle_parts([first_name | other_names]) do
    {middle_names, last_name} = Enum.split(other_names, -1)
    Enum.join(["#{last_name},", first_name, initials(middle_names)], " ")
  end

  defp initials(middle_names) do
    middle_names |> Enum.map(&initial/1) |> Enum.join(" ")
  end

  defp initial(<<_>> = name), do: String.at(name, 0)
  defp initial(name), do: "#{String.at(name, 0)}."
end
