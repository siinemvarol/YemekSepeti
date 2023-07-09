package com.sinem.service;

import com.sinem.dto.request.OrderCreateRequestDto;
import com.sinem.dto.response.OrderFindAllByCustomerResponseDto;
import com.sinem.dto.response.OrderFindAllByRestaurantResponseDto;
import com.sinem.dto.response.OrderFindAllResponseDto;
import com.sinem.exceptions.ErrorType;
import com.sinem.exceptions.YemekSepetiException;
import com.sinem.mapper.IOrderMapper;
import com.sinem.repository.ICustomerRepository;
import com.sinem.repository.IOrderRepository;
import com.sinem.repository.IProductRepository;
import com.sinem.repository.IRestaurantRepository;
import com.sinem.repository.entity.Customer;
import com.sinem.repository.entity.Order;
import com.sinem.repository.entity.Product;
import com.sinem.repository.entity.Restaurant;
import com.sinem.utility.ServiceManager;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Spliterator;
import java.util.stream.Collectors;

@Service
public class OrderService extends ServiceManager<Order,Long> {
    private final IOrderRepository orderRepository;
    private final ICustomerRepository customerRepository;
    private final IRestaurantRepository restaurantRepository;
    private final IProductRepository productRepository;

    public OrderService(IOrderRepository orderRepository, ICustomerRepository customerRepository, IRestaurantRepository restaurantRepository, IProductRepository productRepository) {
        super(orderRepository);
        this.orderRepository = orderRepository;
        this.customerRepository = customerRepository;
        this.restaurantRepository = restaurantRepository;
        this.productRepository = productRepository;
    }

    public Boolean createOrder(Long customerId, Long restaurantId) {
        Optional<Customer> optionalCustomer = customerRepository.findById(customerId);
        Optional<Restaurant> optionalRestaurant = restaurantRepository.findById(restaurantId);
        if(optionalRestaurant.isPresent()){
            if(optionalCustomer.isPresent()){
                if(optionalCustomer.get().isLoggedIn()){
                    OrderCreateRequestDto orderCreateRequestDto = OrderCreateRequestDto.builder()
                            .customer(optionalCustomer.get())
                            .restaurant(optionalRestaurant.get())
                            .build();
                    Order order = IOrderMapper.INSTANCE.fromCreateDtoToOrder(orderCreateRequestDto);
                    orderRepository.save(order);
                } else {
                    throw new YemekSepetiException(ErrorType.ORDER_LOGIN_ERROR);
                }
            }
            else {
                throw new YemekSepetiException(ErrorType.CUSTOMER_NOT_FOUND);
            }
        } else {
            if(optionalCustomer.isEmpty()){
                throw new YemekSepetiException(ErrorType.CUSTOMER_AND_RESTAURANT_NOT_FOUND);
            }
            throw new YemekSepetiException(ErrorType.RESTAURANT_NOT_FOUND);
        }
        return true;
    }

    public Boolean addProductToOrder(Long customerId, Long orderId, Long productId, Long restaurantId) {
        Optional<Customer> optionalCustomer = customerRepository.findById(customerId);
        Optional<Order> optionalOrder = orderRepository.findById(orderId);
        Optional<Product> optionalProduct = productRepository.findById(productId);
        Optional<Restaurant> optionalRestaurant = restaurantRepository.findById(restaurantId);
        if(optionalCustomer.isEmpty()){
            throw new YemekSepetiException(ErrorType.CUSTOMER_NOT_FOUND);
        }
        if(optionalOrder.isEmpty()){
            throw new YemekSepetiException(ErrorType.ORDER_NOT_FOUND);
        }
        if(optionalProduct.isEmpty()){
            throw new YemekSepetiException(ErrorType.PRODUCT_NOT_FOUND);
        }
        if(optionalRestaurant.isEmpty()){
            throw new YemekSepetiException(ErrorType.RESTAURANT_NOT_FOUND);
        }
        if(optionalOrder.get().getCustomer().getId() == customerId){
            if(optionalOrder.get().getRestaurant().getId() == restaurantId){
             //   if(optionalProduct.get().getId())
                optionalOrder.get().getProducts().add(optionalProduct.get());
                update(optionalOrder.get());
            } else {
                throw new YemekSepetiException(ErrorType.INCONSISTENT_ORDER_INFORMATION);
            }
        } else {
            throw new YemekSepetiException(ErrorType.UNAUTHORIZED_ORDER);
        }

        return true;
    }

    public List<OrderFindAllByCustomerResponseDto> findAllOrdersByCustomer(Long customerId) {
        Optional<Customer> optionalCustomer = customerRepository.findById(customerId);
        if(optionalCustomer.isPresent()){
            return optionalCustomer.get().getOrders().stream().map(order -> {
                return OrderFindAllByCustomerResponseDto.builder()
                        .orderId(order.getId())
                        .customerName(order.getCustomer().getName())
                        .restaurantId(order.getRestaurant().getId())
                        .restaurantName(order.getRestaurant().getName())
                        .build();
            }).collect(Collectors.toList());
        } else {
            throw new YemekSepetiException(ErrorType.CUSTOMER_NOT_FOUND);
        }
    }

    public List<OrderFindAllResponseDto> listAllOrders() {
        return orderRepository.findAll().stream().map(order -> {
            return OrderFindAllResponseDto.builder()
                    .orderId(order.getId())
                    .customerName(order.getCustomer().getName())
                    .restaurantId(order.getRestaurant().getId())
                    .restaurantName(order.getRestaurant().getName())
                    .build();
        }).collect(Collectors.toList());
    }

    public List<OrderFindAllByRestaurantResponseDto> findAllOrdersByRestaurant(Long restaurantId) {
        Optional<Restaurant> optionalRestaurant = restaurantRepository.findById(restaurantId);
        if(optionalRestaurant.isPresent()){
            return optionalRestaurant.get().getOrders().stream().map(order -> {
                return OrderFindAllByRestaurantResponseDto.builder()
                        .orderId(order.getId())
                        .customerName(order.getCustomer().getName())
                        .restaurantId(order.getRestaurant().getId())
                        .restaurantName(order.getRestaurant().getName())
                        .build();
            }).collect(Collectors.toList());
        } else {
            throw new YemekSepetiException(ErrorType.RESTAURANT_NOT_FOUND);
        }
    }
}
