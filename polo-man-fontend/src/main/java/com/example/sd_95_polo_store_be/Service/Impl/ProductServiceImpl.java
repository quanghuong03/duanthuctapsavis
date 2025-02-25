package com.example.sd_95_polo_store_be.Service.Impl;

import com.example.sd_95_polo_store_be.Model.Entity.Images;
import com.example.sd_95_polo_store_be.Model.Entity.Products;
import com.example.sd_95_polo_store_be.Model.Request.ProductDetailRepuest;
import com.example.sd_95_polo_store_be.Model.Request.ProductRequest;
import com.example.sd_95_polo_store_be.Model.Request.ProductRequset;
import com.example.sd_95_polo_store_be.Model.Response.*;
import com.example.sd_95_polo_store_be.Repository.*;
import com.example.sd_95_polo_store_be.Service.DiscountService;
import com.example.sd_95_polo_store_be.Service.ProductDetailService;
import com.example.sd_95_polo_store_be.Service.ProductService;
import com.example.sd_95_polo_store_be.common.Mapper.EntityMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.Objects;

@Service
public class ProductServiceImpl implements ProductService {

    private ProductRepository productRepository;
    @Autowired
    private ImageRepository imageRepository;
    @Autowired
    private ProductDetailService productDetailService;
    @Autowired
    private CategoriesRepository categoriesRepository;
    @Autowired
    private BrandRepository brandRepository;
    @Autowired
    private MatarialRepository matarialRepository;
    @Autowired
    private DiscountRepository discountRepository;
    @Autowired
    private DiscountService discountService;

    public ProductServiceImpl(ProductRepository productRepository) {

        this.productRepository = productRepository;
    }

    @Override
    public List<ProductForAdminResponse> getAllProductForAdmin() {
        discountService.expireDiscounts();
        productRepository.updateProductsForExpiredDiscounts();
        var product = productRepository.getAllProductByCreateDateDesc();
        for (ProductForAdminResponse productForAdminResponse : product) {
            var image = productRepository.getImage(productForAdminResponse.getId());
            productForAdminResponse.setImage(image);
            var price = productRepository.getPrice(productForAdminResponse.getId());
        }

        return product;
    }

    @Override
    public List<ProductForAdminResponse> getAllProductForUser() {
        discountService.expireDiscounts();
        productRepository.updateProductsForExpiredDiscounts();
        var productDiscount = productRepository.getProductDiscounts();
        var products = productRepository.getAllProductToDiscount();
        var productStatusActive = products.stream().filter(product -> !product.getStatus().equals(0)).toList();
        for (ProductForAdminResponse product : productStatusActive) {
            var price = productRepository.getPrice(product.getId());
            product.setPrice(price);
            System.out.println(price);
            if (product.getStatus().equals(3)) {
                for (ProductDiscountResponse productDiscountResponse : productDiscount) {
                    if (Objects.equals(product.getId(), productDiscountResponse.getProductId())) {
                        product.setPricecost(product.getPrice() - (product.getPrice() * product.getDiscount()));
                        product.setPromotionPercent(Math.round(product.getDiscount() * 100));
                        break;
                    }
                }
            } else {
                product.setPromotionPercent(0);
                product.setPricecost(price);
            }
            var image = productRepository.getImage(product.getId());
            product.setImage(image);
        }

        return productStatusActive;
    }

    @Override
    public List<ProductForAdminResponse> getAllProduct() {
        discountService.expireDiscounts();
        productRepository.updateProductsForExpiredDiscounts();
        var product = productRepository.getAllProductToDiscount();
        for (ProductForAdminResponse productForAdminResponse : product) {
            var image = productRepository.getImage(productForAdminResponse.getId());
            productForAdminResponse.setImage(image);
            var price = productRepository.getPrice(productForAdminResponse.getId());
        }

        return product;
    }


    @Override
    public void addProduct(ProductRequset productRequset) {
        discountService.expireDiscounts();
        productRepository.updateProductsForExpiredDiscounts();
        Products product = new Products();
        productRepository.save(product);
    }

    @Override
    public GetOneProductResponse getOne(Integer id) {
        var product = productRepository.getId(id).orElseThrow();
        product.setProductDetails(productDetailService.getForProduct(id));
        return product;
    }

    @Override
    public GetOneProductResponse getProductDetail(Integer id) {
        var product = productRepository.getId(id).orElseThrow();
        var productDiscount = productRepository.getProductDiscounts();
        List<ProductDetailResponse> listProductDetails = productDetailService.getForProduct(id);
        for (ProductDetailResponse productDetail : listProductDetails) {
            productDetail.setPrice(productDetail.getPrice());

            if (product.getStatus().equals(3)) {
                productDetail.setPricecost(productDetail.getPrice() - (productDetail.getPrice() * product.getDiscount()));
                product.setPromotionPercent(Math.round(product.getDiscount() * 100));


            } else {
                product.setPromotionPercent(0);
                productDetail.setPricecost(productDetail.getPrice());
            }
            product.setProductDetails(listProductDetails);
        }
        return product;
    }

    @Override
    public void create(ProductRequest productRequest) {
        var now = OffsetDateTime.now();
        var category = categoriesRepository.findById(productRequest.getCategoryId()).orElseThrow();
        var brand = brandRepository.findById(productRequest.getBrandId()).orElseThrow();
        var material = matarialRepository.findById(productRequest.getMaterialId()).orElseThrow();
        var discount = discountRepository.findById(productRequest.getDiscountId()).orElseThrow();
        Products products = new Products();
        products.setName(productRequest.getName());

        products.setDescription(productRequest.getDescription());
        products.setCreateDate(now);
        products.setUpdatedAt(now);
        products.setCategories(category);
        products.setBrands(brand);
        products.setDiscount(discount);
        if (discount.getStatus() == 1 && discount.getId() != 1) {
            products.setStatus(3);
        } else {
            products.setStatus(1);
        }
        products.setMaterials(material);
        productRepository.save(products);
        List<ProductDetailRepuest> productDetailRepuests = productRequest.getProductDetailRepuests();
        productDetailRepuests.forEach(request -> productDetailService.createOrUpdate(request, products.getId()));
    }

    @Override
    public void update(Integer productId, ProductRequest productRequest) {
        var now = OffsetDateTime.now();
        var product = productRepository.findById(productId).orElseThrow();
        var category = categoriesRepository.findById(productRequest.getCategoryId()).orElseThrow();
        var brand = brandRepository.findById(productRequest.getBrandId()).orElseThrow();
        var material = matarialRepository.findById(productRequest.getMaterialId()).orElseThrow();
        var discount = discountRepository.findById(productRequest.getDiscountId()).orElseThrow();
        product.setName(productRequest.getName());
        if (discount.getStatus() == 1 && discount.getId() != 1) {
            product.setStatus(3);
        } else {
            product.setStatus(1);
        }
        product.setDescription(productRequest.getDescription());
        product.setUpdatedAt(now);
        product.setCategories(category);
        product.setBrands(brand);
        product.setDiscount(discount);
        product.setMaterials(material);
        productRepository.save(product);
        List<ProductDetailRepuest> productDetailRepuests = productRequest.getProductDetailRepuests();
        productDetailRepuests.forEach(request -> productDetailService.createOrUpdate(request, product.getId()));
    }

    @Override
    public void changeStatus(Integer id) {
        var products = productRepository.findById(id).orElseThrow();
        if(products.getStatus() == 1 || products.getStatus() == 3){
            products.setStatus(0);
            productRepository.save(products);
        }else if (products.getStatus() == 0) {
            products.setStatus(1);
            productRepository.save(products);
        }

    }

    @Override
    public void changeSatatusDiscount(Integer id) {
        var products = productRepository.findById(id).orElseThrow();
        if(products.getStatus() == 3){
            products.setStatus(1);
            productRepository.save(products);
        }
    }


}
