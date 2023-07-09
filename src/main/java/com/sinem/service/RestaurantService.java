package com.sinem.service;

import com.sinem.dto.request.RestaurantSaveRequestDto;
import com.sinem.dto.response.ProductFindAllResponseDto;
import com.sinem.dto.response.RestaurantFindAllResponseDto;
import com.sinem.exceptions.ErrorType;
import com.sinem.exceptions.YemekSepetiException;
import com.sinem.mapper.IRestaurantMapper;
import com.sinem.repository.IProductRepository;
import com.sinem.repository.IRestaurantRepository;
import com.sinem.repository.entity.Product;
import com.sinem.repository.entity.Restaurant;
import com.sinem.utility.ServiceManager;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class RestaurantService extends ServiceManager<Restaurant, Long> {

    private final IRestaurantRepository restaurantRepository;
    private final IProductRepository productRepository;

    public RestaurantService(IRestaurantRepository restaurantRepository, IProductRepository productRepository) {
        super(restaurantRepository);
        this.restaurantRepository = restaurantRepository;
        this.productRepository = productRepository;
    }

    public Restaurant save(RestaurantSaveRequestDto dto){
        Restaurant restaurant = IRestaurantMapper.INSTANCE.fromSaveDtoToRestaurant(dto);
        return restaurantRepository.save(restaurant);
    }

    public void saveProductToRestaurant(Long productId, Long restaurantId) {
        Optional<Product> optionalProduct = productRepository.findById(productId);
        Optional<Restaurant> optionalRestaurant = restaurantRepository.findById(restaurantId);
        if (optionalProduct.isPresent()){
            if(optionalRestaurant.isPresent()){
                optionalRestaurant.get().getProducts().add(optionalProduct.get());
                update(optionalRestaurant.get());
            } else {
                throw new YemekSepetiException(ErrorType.RESTAURANT_NOT_FOUND);
            }
        } else {
            if(optionalRestaurant.isEmpty()){
                throw new YemekSepetiException(ErrorType.PRODUCT_AND_RESTAURANT_NOT_FOUND);
            }
            throw new YemekSepetiException(ErrorType.PRODUCT_NOT_FOUND);
        }
    }
    public List<RestaurantFindAllResponseDto> findAllRestaurants(){
        return restaurantRepository.findAll().stream().map(restaurant -> {
            return RestaurantFindAllResponseDto.builder()
                    .id(restaurant.getId())
                    .name(restaurant.getName())
                    .point(restaurant.getPoint())
                    .address(restaurant.getAddress())
                    .build();
        }).collect(Collectors.toList());
    }

    public List<ProductFindAllResponseDto> findAllProducts(Long restaurantId) {
        Optional<Restaurant> optionalRestaurant = restaurantRepository.findById(restaurantId);
        if(optionalRestaurant.isPresent()){
            return optionalRestaurant.get().getProducts().stream().map(product -> {
               return ProductFindAllResponseDto.builder()
                       .id(product.getId())
                       .name(product.getName())
                       .category(product.getCategory())
                       .cost(product.getCost())
                       .build();
            }).collect(Collectors.toList());
        } else {
            throw new YemekSepetiException(ErrorType.RESTAURANT_NOT_FOUND);
        }

    }

    /**
     *
     * @param productId
     * @param restaurantId
     * @return
     *    public Boolean doesProductExist(Long productId, Long restaurantId){
     *         boolean check = false;
     *         List<Product> productList =
     *         for()
     *     }
     */

}
