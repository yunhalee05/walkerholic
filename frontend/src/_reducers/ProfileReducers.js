import { FOLLOW_FAIL, FOLLOW_REQUEST, FOLLOW_SUCCESS, UNFOLLOW_FAIL, UNFOLLOW_REQUEST, UNFOLLOW_SUCCESS } from "../_constants/FollowConstants";
import { EDIT_PROFILE_FAIL, EDIT_PROFILE_REQUEST, EDIT_PROFILE_SUCCESS, GET_PROFILE_FAIL, GET_PROFILE_REQUEST, GET_PROFILE_SUCCESS } from "../_constants/ProfileConstants";

export const profileReducer = (state={}, action)=>{
    switch(action.type){
        case GET_PROFILE_REQUEST:
            return {...state, loading:true}
        case GET_PROFILE_SUCCESS:
            return {...state, loading:false, user:action.payload.user, followers:action.payload.followers, followings:action.payload.followings, posts:action.payload.posts, likePosts:action.payload.likePosts}
        case GET_PROFILE_FAIL:
            return {...state, loading:false, error:action.payload}

        case EDIT_PROFILE_REQUEST:
            return {...state, loading:true}
        case EDIT_PROFILE_SUCCESS:
            return {...state, loading:false, user:action.payload}
        case EDIT_PROFILE_FAIL:
            return {...state, loading:false, error:action.payload}

        case FOLLOW_REQUEST:
            return {...state, loading:true}
        case FOLLOW_SUCCESS:
            if(state.user.id === action.payload.id){
                return {...state, loading:false, followings: [...state.followings,action.payload.follow]}
            }else{
                return {...state, loading:false, followers: [...state.followers,action.payload.followed]}
            }
        case FOLLOW_FAIL:
            return {...state, loading:false, error:action.payload}

        case UNFOLLOW_REQUEST:
            return {...state, loading:true}
        case UNFOLLOW_SUCCESS:
            if(state.user.id === action.payload.id){
                return {...state, loading:false, followings: state.followings.filter(follow=>follow.id!==action.payload)}
            }else{
                return {...state, loading:false, followers: state.followers.filter(follow=> follow.id!==action.payload)}
            }
        case UNFOLLOW_FAIL:
            return {...state, loading:false, error:action.payload}

            


        default:
            return state;
    }
}