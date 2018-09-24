package com.yy.guava.demo;

import com.google.common.util.concurrent.*;
import lombok.Data;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.concurrent.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ListenFutureTest {

    //business executor.
    final static ExecutorService executor = Executors.newFixedThreadPool(1);

    @Test
    public void listenFuture01() throws InterruptedException {

        Cook cook = new Cook();
        Order order = new Order();


        ListenableFuture<Meal> future = cook.prepareMeal(order);

        FutureCallback<Meal> callback = new FutureCallback<Meal>() {
            @Override
            public void onSuccess(Meal meal) {
                // Eat your meal
                System.out.println("eat meal." + meal.getName());
            }

            @Override
            public void onFailure(Throwable throwable) {
                // Something went wrong, complain to the manager
                System.out.println("cook meal fail." + throwable.getLocalizedMessage());
            }
        };

        Futures.addCallback(future, callback, executor);

        // Pay at cash register and find a table
        future.cancel(true);
        executor.awaitTermination(10, TimeUnit.DAYS);
    }

    @Test
    public void listenFuture02() throws InterruptedException, TimeoutException, ExecutionException {

        Cook cook = new Cook();
        Order order = new Order();


        ListenableFuture<Meal> future = cook.prepareMeal02(order);

        FutureCallback<Meal> callback = new FutureCallback<Meal>() {
            @Override
            public void onSuccess(Meal meal) {
                // Eat your meal
                System.out.println("eat meal." + meal.getName());
            }

            @Override
            public void onFailure(Throwable throwable) {
                // Something went wrong, complain to the manager
                System.out.println("cook meal fail." + throwable.getLocalizedMessage());
            }
        };

        Futures.addCallback(future, callback, executor);


//        future.cancel(false);

        long startTime = System.currentTimeMillis();
        future.get(5, TimeUnit.SECONDS);
        System.err.println("cost:" + (System.currentTimeMillis() - startTime));

        // Pay at cash register and find a table
        executor.awaitTermination(10, TimeUnit.DAYS);
    }
}

@Data
class Meal{
    String name;
}
class Order{

}
class Cook{

    //business executor.
    final static Executor executor = Executors.newFixedThreadPool(1);


    private static  final ListeningExecutorService executorService = MoreExecutors.listeningDecorator(Executors.newCachedThreadPool());


    /**
     * 版本1
     * @param order
     * @return
     */
    public ListenableFuture<Meal> prepareMeal(Order order) {
        SettableFuture<Meal> future = SettableFuture.create();

        // Prepare the meal in a different thread
        executor.execute(() -> {
            // Prepare the meal
            Meal meal = new Meal();
            meal.setName("xigua");

            try {
                Thread.sleep(5000l);
            } catch (InterruptedException e) {
                System.err.println("interrupted." + e.getLocalizedMessage());
            }

            // Set the result of the future
            future.set(meal);
        });

        return future;
    }

    public ListenableFuture<Meal> prepareMeal02(Order order) {
        return executorService.submit(() -> {
            Meal meal = new Meal();
            meal.setName("土豆丝");

            Thread.sleep(4000l);
            return meal;
        });
    }
}
