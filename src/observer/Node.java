package observer;

public class Node extends Element {
    private int id;
    private String name;
    private int age;
    private String address;

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        firePropertyChange("address", this.address, address);
        this.address = address;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        // firePropertyChange("id",this.id,id);
        getPropertyChangeSupport().fireIndexedPropertyChange("id", 1, this.id, id);
        this.id = id;
    }

    public String getName() {
        return name;
    }

    /**
     * 注意fire的时候代码要在this.name = name; 之前，因为它会自动判断修改前后的值，写在this.name =
     * name;之后的话因为this.name已经等于name,所以它不会真的发布事件，必须写在this.name = name;前面
     * 
     * @param name
     */
    public void setName(String name) {
        if (!name.equals(this.name)) {
            firePropertyChange("name", this.name, name);
        }
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

}