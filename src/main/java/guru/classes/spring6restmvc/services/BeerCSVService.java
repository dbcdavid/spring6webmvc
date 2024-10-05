package guru.classes.spring6restmvc.services;

import guru.classes.spring6restmvc.model.BeerCSVRecord;

import java.io.File;
import java.util.List;

public interface BeerCSVService {
    List<BeerCSVRecord> convertCSV(File file);
}
