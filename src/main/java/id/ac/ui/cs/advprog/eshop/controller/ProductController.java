package id.ac.ui.cs.advprog.eshop.controller;

import id.ac.ui.cs.advprog.eshop.model.Car;
import id.ac.ui.cs.advprog.eshop.model.Product;
import id.ac.ui.cs.advprog.eshop.service.CarServiceImpl;
import id.ac.ui.cs.advprog.eshop.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductService service;

    @GetMapping("/create")
    public String createProductPage(Model model) {
        Product product = new Product();
        model.addAttribute("product", product);
        return "createProduct";
    }

    @PostMapping("/create")
    public String createProductPost(@ModelAttribute Product product) {
        service.create(product);
        return "redirect:list";
    }

    @GetMapping("/list")
    public String productListPage(Model model) {
        List<Product> allProducts = service.findAll();
        model.addAttribute("products", allProducts);
        return "productList";
    }

    @GetMapping("/edit/{id}")
    public String editProductPage(@PathVariable String id, Model model) {
        Product product = service.findById(id);
        if (product == null) {
            return "redirect:/product/list";
        }
        model.addAttribute("product", product);
        return "editProduct";
    }

    @PostMapping("/edit")
    public String editProductPost(@ModelAttribute Product product) {
        service.update(product);
        return "redirect:list";
    }

    @GetMapping("/delete/{id}")
    public String deleteProductGet(@PathVariable String id){
        Product product = service.findById(id);
        if (product == null) {
            return "redirect:/product/list";
        }
        service.delete(product);
        return "redirect:/product/list";
    }
}


@Controller
@RequestMapping("/car")
class CarController extends ProductController {

    @Autowired
    private CarServiceImpl carservice;

    @GetMapping("/createCar")
    public String createCarPage(Model model) {
        Car car = new Car();
        model.addAttribute("car", car);
        return "createCar";
    }

    @PostMapping("/createCar")
    public String createCarPost(@ModelAttribute Car car, Model model) {
        carservice.create(car);
        return "redirect:listCar";
    }

    @GetMapping("/listCar")
    public String carListPage(Model model) {
        List<Car> allCars = carservice.findAll();
        model.addAttribute("cars", allCars);
        return "carList";
    }


    @GetMapping("/editCar/{carId}")
    public String editCarPage (@PathVariable String carId, Model model) {
        Car car = carservice.findById(carId);
        if (car == null) {
            return "redirect:/car/list";
        }
        model.addAttribute("car", car); return "editCar";
    }

    @PostMapping("/editCar")
    public String editCarPost (@ModelAttribute Car car, Model model) {
        System.out.println(car.getCarId());
        carservice.update(car);
        return "redirect:listCar";
    }

    @GetMapping("/deleteCar/{carId}")
    public String deleteCarGet (@PathVariable String carId) {
        Car car = carservice.findById(carId);
        if (car == null) {
            return "redirect:/car/list";
        }
        carservice.delete(car);
        return "redirect:listCar";
    }
}