package id.ac.ui.cs.advprog.eshop.controller;

import id.ac.ui.cs.advprog.eshop.model.Car;
import id.ac.ui.cs.advprog.eshop.service.CarServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/car")
class CarController extends ProductController {

    @Autowired
    private CarServiceImpl carservice;

    @GetMapping("/create")
    public String createCarPage(Model model) {
        Car car = new Car();
        model.addAttribute("car", car);
        return "createCar";
    }

    @PostMapping("/create")
    public String createCarPost(@ModelAttribute Car car) {
        carservice.create(car);
        return "redirect:listCar";
    }

    @GetMapping("/list")
    public String carListPage(Model model) {
        List<Car> allCars = carservice.findAll();
        model.addAttribute("cars", allCars);
        return "carList";
    }


    @GetMapping("/edit/{carId}")
    public String editCarPage (@PathVariable String carId, Model model) {
        Car car = carservice.findById(carId);
        if (car == null) {
            return "redirect:/car/list";
        }
        model.addAttribute("car", car); return "editCar";
    }

    @PostMapping("/edit")
    public String editCarPost (@ModelAttribute Car car) {
        System.out.println(car.getCarId());
        carservice.update(car);
        return "redirect:listCar";
    }

    @GetMapping("/delete/{carId}")
    public String deleteCarGet (@PathVariable String carId) {
        Car car = carservice.findById(carId);
        if (car == null) {
            return "redirect:/car/list";
        }
        carservice.delete(car);
        return "redirect:listCar";
    }
}
