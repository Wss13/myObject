package com.example.server;

import com.example.dto.User;
import com.netflix.hystrix.HystrixCommandGroupKey;
import com.netflix.hystrix.HystrixObservableCommand;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import rx.Observable;
import rx.Subscriber;

public class userObservableCommand extends HystrixObservableCommand<User> {
    private Long id;
    private RestTemplate restTemplate;
    public userObservableCommand(Setter setter, RestTemplate restTemplate,Long id) {
        super(setter);
        this.id = id;
        this.restTemplate = restTemplate;
    }

    @Override
    protected Observable<User> construct() {
        return Observable.create(new Observable.OnSubscribe<User>() {
            @Override
            public void call(Subscriber<? super User> subscriber) {
                try {
                    if(!subscriber.isUnsubscribed()){
                        User user = restTemplate.getForObject("http://HELLO-SERVER/users?id={1}",User.class,id);
                        subscriber.onNext(user);
                        subscriber.onCompleted();
                    }
                }catch (Exception e){

                }
            }
        });
    }
}
