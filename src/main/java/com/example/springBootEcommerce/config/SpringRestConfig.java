package com.example.springBootEcommerce.config;

import com.example.springBootEcommerce.entity.Country;
import com.example.springBootEcommerce.entity.Product;
import com.example.springBootEcommerce.entity.ProductCategory;
import com.example.springBootEcommerce.entity.State;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurer;
import org.springframework.http.HttpMethod;
import org.springframework.web.servlet.config.annotation.CorsRegistry;

import javax.persistence.EntityManager;
import javax.persistence.metamodel.EntityType;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Configuration
public class SpringRestConfig implements RepositoryRestConfigurer {



    @Value("${allowed.origins}")
    private String[] theAllowedOrigins;

    @Autowired
    private EntityManager entityManager;
    @Override
    public void configureRepositoryRestConfiguration(RepositoryRestConfiguration configuration, CorsRegistry cors) {
//        RepositoryRestConfigurer.super.configureRepositoryRestConfiguration(config, cors);
        HttpMethod[] theUnsupportedActions = {HttpMethod.PUT, HttpMethod.POST, HttpMethod.DELETE};

        // disable HTTP methods for ProductCategory: PUT, POST and DELETE
        disableHttpMethods(Product.class, configuration, theUnsupportedActions);
        disableHttpMethods(ProductCategory.class, configuration, theUnsupportedActions);
        disableHttpMethods(Country.class, configuration, theUnsupportedActions);
        disableHttpMethods(State.class, configuration, theUnsupportedActions);

         exposeIds(configuration);
         cors.addMapping(configuration.getBasePath() + "/**").allowedOrigins(theAllowedOrigins);
    }

    private void disableHttpMethods(Class theClass, RepositoryRestConfiguration configuration, HttpMethod[] theUnsupportedActions) {
        configuration.getExposureConfiguration()
                .forDomainType(theClass)
                .withItemExposure((metdata, httpMethods) -> httpMethods.disable(theUnsupportedActions))
                .withCollectionExposure((metdata, httpMethods) -> httpMethods.disable(theUnsupportedActions));

    }


    private void exposeIds(RepositoryRestConfiguration configuration) {

        Set<EntityType<?>> entityTypes = entityManager.getMetamodel().getEntities();

        List<Class> classList = new ArrayList<>();

        for (EntityType type : entityTypes)
        {
            classList.add(type.getJavaType());
        }

        Class[] classes = classList.toArray(new Class[0]);
        configuration.exposeIdsFor(classes);
    }
}
