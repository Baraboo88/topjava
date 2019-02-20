package ru.javawebinar.topjava.model;

public abstract class AbstractNamedEntity extends AbstractBaseEntity {

    protected String name;
    protected String surname;

    protected AbstractNamedEntity(Integer id, String name, String surname) {
        super(id);
        this.name = name;
        this.surname = surname;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    @Override
    public String toString() {
        return String.format("Entity %s (%s, '%s', '%s')", getClass().getName(), id, name, surname);
    }
}