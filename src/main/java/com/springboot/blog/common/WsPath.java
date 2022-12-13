package com.springboot.blog.common;

/** The interface will hold the request mapping path to */
public interface WsPath {

    /** API version 1 */
    String API_V_1 = "/api/1";

    String POSTS = API_V_1 + "/posts";

    /**Ws path to all posts data**/
    String CREATE_POST = POSTS;

    /**Ws path to Update post **/
    String GET_POST = POSTS + "/{id}";

    /**Ws path to Update post **/
    String UPDATE_POST = POSTS + "/{id}";

    /**Ws path to Delete post **/
    String DELETE_POST = POSTS + "/{id}";
}
