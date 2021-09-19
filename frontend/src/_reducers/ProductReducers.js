import { GET_PRODUCTS_FAIL, GET_PRODUCTS_REQUEST, GET_PRODUCTS_SUCCESS, GET_PRODUCT_FAIL, GET_PRODUCT_REQUEST, GET_PRODUCT_SUCCESS } from "../_constants/ProductConstants";

export const productsReducer = (state={}, action)=>{
    switch(action.type){
    
        case GET_PRODUCTS_REQUEST:
            return {...state, loading:true}
        case GET_PRODUCTS_SUCCESS:
            return {...state, loading:false, products:action.payload.products, totalElement:action.payload.totalElement, totalPage:action.payload.totalPage}
        case GET_PRODUCTS_FAIL:
            return {...state, loading:false, error:action.payload}
    
        case GET_PRODUCT_REQUEST:
            return {...state, loading:true}
        case GET_PRODUCT_SUCCESS:
            return {...state, loading:false, product:action.payload}
        case GET_PRODUCT_FAIL:
            return {...state, loading:false, error:action.payload}

        default:
            return state;
    }
}