package models;

public abstract class Model {
    protected Long id;
    protected String tableName;

    public Model(String tableName) {
        this.tableName = tableName;
    }
}
