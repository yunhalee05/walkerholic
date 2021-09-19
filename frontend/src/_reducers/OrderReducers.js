import { ADD_TO_CART_FAIL, ADD_TO_CART_REQUEST, ADD_TO_CART_SUCCESS, CREATE_CART_FAIL, CREATE_CART_REQUEST, CREATE_CART_SUCCESS, GET_CARTITEMS_FAIL, GET_CARTITEMS_REQUEST, GET_CARTITEMS_SUCCESS } from "../_constants/OrderConstants";

export const cartReducer = (state={}, action)=>{
    switch(action.type){
    
        case GET_CARTITEMS_REQUEST:
            return {...state, loading:true}
        case GET_CARTITEMS_SUCCESS:
            return {...state, loading:false, ...action.payload}
        case GET_CARTITEMS_FAIL:
            return {...state, loading:false, error:action.payload}

        case CREATE_CART_REQUEST:
            return {...state, loading:true}
        case CREATE_CART_SUCCESS:
            return {...state, loading:false, id:action.payload}
        case CREATE_CART_FAIL:
            return {...state, loading:false, error:action.payload}

        case ADD_TO_CART_REQUEST:
            return {...state, loading:true}
        case ADD_TO_CART_SUCCESS:
            if(state.orderItems.filter(o=>o.id===action.payload.id).length===1){
                return {...state, loading:false, orderItems:state.orderItems.map(o=>o.id===action.payload.id? action.payload : o)}
            }else{
                return {...state, loading:false, orderItems:[...state.orderItems, action.payload]}
            }
        case ADD_TO_CART_FAIL:
            return {...state, loading:false, error:action.payload}

        default:
            return state;
    }
}