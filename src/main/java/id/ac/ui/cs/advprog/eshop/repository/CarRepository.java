package id.ac.ui.cs.advprog.eshop.repository;

import id.ac.ui.cs.advprog.eshop.model.Car;

import java.util.Iterator;

public interface CarRepository {
    public Car create(Car car);
    public Car delete(Car car);
    public Car findById(String id);
    public Car update(Car car);
    public Iterator<Car> findAll();
}
