import { GET_PRODUCTS_FAIL, GET_PRODUCTS_REQUEST, GET_PRODUCTS_SUCCESS, GET_PRODUCT_FAIL, GET_PRODUCT_REQUEST, GET_PRODUCT_SUCCESS } from "../_constants/ProductConstants";
import { CREATE_REVIEW_FAIL, CREATE_REVIEW_REQUEST, CREATE_REVIEW_SUCCESS, DELETE_REVIEW_FAIL, DELETE_REVIEW_REQUEST, DELETE_REVIEW_SUCCESS, EDIT_REVIEW_FAIL, EDIT_REVIEW_REQUEST, EDIT_REVIEW_SUCCESS } from "../_constants/ReviewConstants";

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

        case CREATE_REVIEW_REQUEST:
            return {...state, reviewloading:true}
        case CREATE_REVIEW_SUCCESS:
            if(state.product.id===action.payload.productId){
                return {...state, product:{...state.product,productReviews:[...state.product.productReviews,action.payload]}}
            }
            return {...state, reviewloading:false, product:action.payload}
        case CREATE_REVIEW_FAIL:
            return {...state, reviewloading:false, error:action.payload}
        
        case EDIT_REVIEW_REQUEST:
            return {...state, reviewloading:true}
        case EDIT_REVIEW_SUCCESS:
            return {...state, product:{...state.product, productReviews:state.product.productReviews.map(review=>review.id===action.payload.id ? action.payload : review)}}
        case EDIT_REVIEW_FAIL:
            return {...state, reviewloading:false, error:action.payload}
        
        case DELETE_REVIEW_REQUEST:
            return {...state, reviewloading:true}
        case DELETE_REVIEW_SUCCESS:
            return {...state, product:{...state.product, productReviews:state.product.productReviews.filter(review=>review.id!==action.payload)}}
        case DELETE_REVIEW_FAIL:
            return {...state, reviewloading:false, error:action.payload}
        
        
    

        default:
            return state;
    }
}