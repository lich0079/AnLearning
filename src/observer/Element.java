package observer;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

/**
 * д��������һ�����ԣ��ֶΣ�ʹ��PropertyChangeSupport��ע�⹹���ʱ����봫������
 * Ȼ������addPropertyChangeListener,��remove�ķ�������2������һ��д�������У���������ſ���ʵʱ����Ӽ�������
 * ��Ҳ����д��getPropertyChangeSupport������Ȼ��������ȥ���listener,��һ��û������������������һ����Ҫ�����ü�����
 * 
 * @author lich0079@gmail.com
 * 
 */
public abstract class Element {
    PropertyChangeSupport listeners = new PropertyChangeSupport(this);

    protected void addPropertyChangeListener(PropertyChangeListener listener) {
        listeners.addPropertyChangeListener(listener);
    }

    protected void addPropertyChangeListener(String propertyName, PropertyChangeListener listener) {
        listeners.addPropertyChangeListener(propertyName, listener);
    }

    protected void removePropertyChangeListener(PropertyChangeListener listener) {
        listeners.removePropertyChangeListener(listener);
    }

    protected void firePropertyChange(String propertyName, Object oldValue, Object newValue) {
        listeners.firePropertyChange(propertyName, oldValue, newValue);
    }

    protected PropertyChangeSupport getPropertyChangeSupport() {
        return listeners;
    }

}