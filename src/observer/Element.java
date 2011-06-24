package observer;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

/**
 * 写个基类设一个属性（字段）使用PropertyChangeSupport，注意构造的时候必须传入自身。
 * 然后设置addPropertyChangeListener,与remove的方法，这2个方法一般写到基类中，这样子类才可以实时的添加监听器，
 * （也可以写个getPropertyChangeSupport方法，然后让子类去添加listener,但一般没看人这样做过）。这一步主要是设置监听器
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