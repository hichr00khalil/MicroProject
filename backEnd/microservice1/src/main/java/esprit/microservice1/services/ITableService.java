package esprit.microservice1.services;

import esprit.microservice1.entities.Ttable;

import java.util.List;

public interface ITableService {
    public List<Ttable> getAllTables();
    public Ttable getTableById(Long id);
    public Ttable createTable(Ttable table);
    public Ttable updateTable(Long id, Ttable newTable);
    public void deleteTable(Long id);
}
