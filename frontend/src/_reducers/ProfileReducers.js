import { GET_PROFILE_FAIL, GET_PROFILE_REQUEST, GET_PROFILE_SUCCESS } from "../_constants/ProfileConstants";

export const profileReducer = (state={}, action)=>{
    switch(action.type){
        case GET_PROFILE_REQUEST:
            return {...state, loading:true}
        case GET_PROFILE_SUCCESS:
            return {...state, loading:false, user:{...action.payload.user, followers:action.payload.followers, followings:action.payload.followings}, posts:action.payload.posts, likePosts:action.payload.likePosts}
        case GET_PROFILE_FAIL:
            return {...state, loading:false, error:action.payload}
        default:
            return state;
    }
}