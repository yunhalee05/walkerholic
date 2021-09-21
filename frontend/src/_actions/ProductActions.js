import axios from "axios"
import { GET_PRODUCTS_FAIL, GET_PRODUCTS_REQUEST, GET_PRODUCTS_SUCCESS, GET_PRODUCT_FAIL, GET_PRODUCT_REQUEST, GET_PRODUCT_SUCCESS } from "../_constants/ProductConstants"

export const getProducts = (page, sort, category,keyword) =>async(dispatch, getState)=>{

    dispatch({
        type:GET_PRODUCTS_REQUEST
    })

    const param = {
        sort : sort? sort :'',
        category : category ? category :'',
        keyword : keyword ? keyword : ''
    }

    try{
        const res = await axios.get(`/products/${page}`,{params:param})

        dispatch({
            type:GET_PRODUCTS_SUCCESS,
            payload:res.data
        })

        return res.data.products


    }catch(error){
        dispatch({
            type:GET_PRODUCTS_FAIL,
            payload:error.response.data
            
        })
    }
}
export const getProduct = (id) =>async(dispatch, getState)=>{

    dispatch({
        type:GET_PRODUCT_REQUEST
    })

    try{
        const res = await axios.get(`/product/${id}`)

        dispatch({
            type:GET_PRODUCT_SUCCESS,
            payload:res.data
        })


    }catch(error){
        dispatch({
            type:GET_PRODUCT_FAIL,
            payload:error.response.data
            
        })
    }
}