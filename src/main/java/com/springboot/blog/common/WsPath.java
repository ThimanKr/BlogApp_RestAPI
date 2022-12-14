package com.springboot.blog.common;

/** The interface will hold the request mapping path to */
public interface WsPath {

    /** API version 1 */
    String API_V_1 = "/api/1";

    /** ############################## POST DATA RESOURCES ##########################*/
    String POSTS = API_V_1 + "/posts";

    /**Ws path to all posts data**/
    String CREATE_POST = POSTS;

    /**Ws path to Update post **/
    String GET_POST = POSTS + "/{id}";

    /**Ws path to Update post **/
    String UPDATE_POST = POSTS + "/{id}";

    /**Ws path to Delete post **/
    String DELETE_POST = POSTS + "/{id}";

    /** ############################## COMMENT DATA RESOURCES ##########################*/


    /**Ws path to get all comments of a post **/
    String COMMENTS = POSTS + "/{postId}/comments";

    /**Ws path to get comment of a post by comment Id **/
    String GET_COMMENT = COMMENTS + "/{id}";

    /**Ws path to create a new comment on a post **/
    String CREATE_COMMENT = COMMENTS;

    /**Ws path to update a comment of a post **/
    String UPDATE_COMMENT = COMMENTS + "/{id}";

    /**Ws path to delete a comment of a post **/
    String DELETE_COMMENT = COMMENTS + "/{id}";
}
