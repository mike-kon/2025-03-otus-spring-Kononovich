package ru.otus.homework.dao;

import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import java.io.FileReader;
import java.io.Reader;
import java.net.URL;
import java.util.List;
import lombok.RequiredArgsConstructor;
import ru.otus.homework.config.TestFileNameProvider;
import ru.otus.homework.dao.dto.QuestionDto;
import ru.otus.homework.domain.Question;
import ru.otus.homework.exceptions.QuestionReadException;

@RequiredArgsConstructor
public class CsvQuestionDao implements QuestionDao {

  private static final char QUOTE_DEILM = ';';

  private final TestFileNameProvider fileNameProvider;

  @Override
  public List<Question> findAll() {
    String filename = fileNameProvider.getTestFileName();
    URL filepath = getClass().getClassLoader().getResource(filename);
    if (filepath == null) {
      throw new QuestionReadException("resource " + filename + " not found");
    }
    try {
      Reader reader = new FileReader(filepath.getPath());
      CsvToBean<QuestionDto> csv = new CsvToBeanBuilder<QuestionDto>(reader)
          .withType(QuestionDto.class)
          .withSkipLines(1)
          .withSeparator(QUOTE_DEILM)
          .build();
      List<QuestionDto> list = csv.parse();
      return list.stream()
          .map(QuestionDto::toDomainObject)
          .toList();
    } catch (Throwable e) {
      throw new QuestionReadException(e.getMessage());
    }
  }
}
