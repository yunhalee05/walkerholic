import { GET_ORDER_LIST_FAIL, GET_ORDER_LIST_REQUEST, GET_ORDER_LIST_SUCCESS } from "../_constants/OrderConstants";
import { DELETE_USER_FAIL, DELETE_USER_REQUEST, DELETE_USER_SUCCESS, GET_USER_LIST_FAIL, GET_USER_LIST_REQUEST, GET_USER_LIST_SUCCESS } from "../_constants/UserConstants";

export const listReducer = (state={}, action)=>{
    switch(action.type){
        case GET_ORDER_LIST_REQUEST:
            return {...state, loading:true}
        case GET_ORDER_LIST_SUCCESS:
            return {...state, loading:false, orders:action.payload}
        case GET_ORDER_LIST_FAIL:
            return {...state, loading:false, error:action.payload}
    
        case GET_USER_LIST_REQUEST:
            return {...state, loading:true}
        case GET_USER_LIST_SUCCESS:
            return {...state, loading:false, users:action.payload}
        case GET_USER_LIST_FAIL:
            return {...state, loading:false, error:action.payload}
    
    
        case DELETE_USER_REQUEST:
            return {...state, loading:true}
        case DELETE_USER_SUCCESS:
            return {...state, loading:false, users:{...state.users, users:state.users.users.filter(user=> user.id!==action.payload)}}
        case DELETE_USER_FAIL:
            return {...state, loading:false, error:action.payload}
    

    
        default:
            return state;
    }
}