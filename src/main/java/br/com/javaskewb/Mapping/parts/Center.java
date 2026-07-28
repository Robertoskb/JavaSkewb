package br.com.javaskewb.Mapping.parts;

public class Center {
    private int id;
    private int value;

    public Center(int id){
        setId(id);
        setValue(id);
    }

    public Center(int id, int value){
        setId(id);
        setValue(value);
    }

    @Override
    public boolean equals(Object obj){
        Center other = (Center) obj;
        return other.getId() == id && (other.getValue() == -1 || other.getValue() == value);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    @Override
    public String toString(){
        return "(" + id + ", " + value + ")";
    }

}
