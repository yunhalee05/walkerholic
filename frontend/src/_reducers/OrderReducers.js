import { ADD_TO_CART_FAIL, ADD_TO_CART_REQUEST, ADD_TO_CART_SUCCESS, CHECKOUT, CREATE_CART_FAIL, CREATE_CART_REQUEST, CREATE_CART_SUCCESS, CREATE_ORDER_FAIL, CREATE_ORDER_REQUEST, CREATE_ORDER_SUCCESS, DELETE_ORDERITEM_FAIL, DELETE_ORDERITEM_REQUEST, DELETE_ORDERITEM_SUCCESS, GET_CARTITEMS_FAIL, GET_CARTITEMS_REQUEST, GET_CARTITEMS_SUCCESS, UPDATE_ORDERITEM_QTY_FAIL, UPDATE_ORDERITEM_QTY_REQUEST, UPDATE_ORDERITEM_QTY_SUCCESS } from "../_constants/OrderConstants";

export const cartReducer = (state={}, action)=>{
    switch(action.type){
    
        case GET_CARTITEMS_REQUEST:
            return {...state, loading:true}
        case GET_CARTITEMS_SUCCESS:
            return {...state, loading:false, success:true, ...action.payload}
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

        
        case UPDATE_ORDERITEM_QTY_REQUEST:
            return {...state, loading:true}
        case UPDATE_ORDERITEM_QTY_SUCCESS:
            return {...state, loading:false, orderItems:state.orderItems.map(o=>o.id===action.payload.id? {...o, qty:action.payload.qty}:o)}
        case UPDATE_ORDERITEM_QTY_FAIL:
            return {...state, loading:false, error:action.payload} 
        
        case DELETE_ORDERITEM_REQUEST:
            return {...state, loading:true}
        case DELETE_ORDERITEM_SUCCESS:
            return {...state, loading:false, orderItems:state.orderItems.filter(o=>o.id!==action.payload)}
        case DELETE_ORDERITEM_FAIL:
            return {...state, loading:false, error:action.payload}
    
        case CHECKOUT:
            return {...state, checkout:true}

        case CREATE_ORDER_REQUEST:
            return {...state, loading:true}
        case CREATE_ORDER_SUCCESS:
            return {...state, loading:false, orderItems:[], id:null, checkout:false }
        case CREATE_ORDER_FAIL:
            return {...state, loading:false, error:action.payload}

        default:
            return state;
    }
}


