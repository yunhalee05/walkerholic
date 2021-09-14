import { GET_DISCOVER_POSTS_FAIL, GET_DISCOVER_POSTS_REQUEST, GET_DISCOVER_POSTS_SUCCESS, GET_FOLLOWINGS_POSTS_FAIL, GET_FOLLOWINGS_POSTS_REQUEST, GET_FOLLOWINGS_POSTS_SUCCESS } from "../_constants/PostConstants";

export const discoverReducer = (state={}, action)=>{
    switch(action.type){
        case GET_DISCOVER_POSTS_REQUEST:
            return {...state, loading:true}
        case GET_DISCOVER_POSTS_SUCCESS:
            return {...state, loading:false, posts:action.payload.posts, totalElement:action.payload.totalElement, totalPage:action.payload.totalPage}
        case GET_DISCOVER_POSTS_FAIL:
            return {...state, loading:false, error:action.payload}
        default:
            return state;
    }
}

export const followingPostsReducer = (state={}, action)=>{
    switch(action.type){
        case GET_FOLLOWINGS_POSTS_REQUEST:
            return {...state, loading:true}
        case GET_FOLLOWINGS_POSTS_SUCCESS:
            return {...state, loading:false, posts:action.payload.posts, totalElement:action.payload.totalElement, totalPage:action.payload.totalPage}
        case GET_FOLLOWINGS_POSTS_FAIL:
            return {...state, loading:false, error:action.payload}
        default:
            return state;
    }
}

