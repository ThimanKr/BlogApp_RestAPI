package com.springboot.blog.common;

/** The interface will hold the request mapping path to */
public interface WsPath {

    /** API version 1 */
    String API_V_1 = "/api/1";

    /** ############################## POST DATA RESOURCES ##########################*/

    /**Ws path to all posts data **/
    String POSTS = API_V_1 + "/posts";

    /**Ws path to create post**/
    String CREATE_POST = API_V_1 + "/post";

    /**Ws path to particular post operations **/
    String POST = API_V_1 + "/post/{id}";


}
