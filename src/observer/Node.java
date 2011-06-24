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
     * ע��fire��ʱ�����Ҫ��this.name = name; ֮ǰ����Ϊ�����Զ��ж��޸�ǰ���ֵ��д��this.name =
     * name;֮��Ļ���Ϊthis.name�Ѿ�����name,������������ķ����¼�������д��this.name = name;ǰ��
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