package id.ac.ui.cs.advprog.eshop.common;

import java.util.List;

public interface CrudService<T> extends Createable<T>, Findable<T>,
        Updateable<T>, Deleteable<T> {
    List<T> findAll();
}
