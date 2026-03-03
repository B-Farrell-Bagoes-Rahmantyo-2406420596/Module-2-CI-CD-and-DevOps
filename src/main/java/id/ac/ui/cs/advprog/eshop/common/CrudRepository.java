package id.ac.ui.cs.advprog.eshop.common;

import java.util.Iterator;

public interface CrudRepository<T> extends Createable<T>, Findable<T>,
        Updateable<T>, Deleteable<T> {
    Iterator<T> findAll();
}

