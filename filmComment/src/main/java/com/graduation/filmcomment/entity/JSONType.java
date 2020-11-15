package com.graduation.filmcomment.entity;

public class JSONType {
    private String name;
    private String value;
    private boolean selected;

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "JSONType{" +
                "name='" + name + '\'' +
                ", value='" + value + '\'' +
                ", selected=" + selected +
                '}';
    }
}
