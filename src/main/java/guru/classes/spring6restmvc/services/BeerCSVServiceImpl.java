package guru.classes.spring6restmvc.services;

import com.opencsv.bean.CsvToBeanBuilder;
import guru.classes.spring6restmvc.model.BeerCSVRecord;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class BeerCSVServiceImpl implements BeerCSVService {
    @Override
    public List<BeerCSVRecord> convertCSV(File csvFile) {
        try {
            List<BeerCSVRecord> records = new CsvToBeanBuilder<BeerCSVRecord>(new FileReader(csvFile))
                    .withType(BeerCSVRecord.class)
                    .build().parse();

            return records;
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
