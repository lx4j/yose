package yose.core;

/**
 * Created by L.x on 15-5-28.
 */
public interface YoseServer {
    void start();

    void stop();

    void setViewResolver(ViewResolver viewResolver);
}
