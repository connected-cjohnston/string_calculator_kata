class StringCalculator

  def add(str)
    return 0 if str.empty?
    return str.to_i unless str.include?(',') || str.include?('\n')

    if str.include?('//')
      head, tail = str.split('\n')
      delimiter = head.sub('//', '')
      ints = tail.split(delimiter)
    else
      ints = str.split(/[\\n,]/)
    end

    negatives = ints.filter { |i| i.to_i < 0 }
    raise StandardError, "negatives not allowed: #{negatives.join(',')}" if negatives.length > 0

    ints.filter { |i| i.to_i <= 1000 }.sum { |x| x.to_i }
  end
end
