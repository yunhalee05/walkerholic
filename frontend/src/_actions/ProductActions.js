import axios from "axios"
import { GET_PRODUCTS_FAIL, GET_PRODUCTS_REQUEST, GET_PRODUCTS_SUCCESS } from "../_constants/ProductConstants"

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


    }catch(error){
        dispatch({
            type:GET_PRODUCTS_FAIL,
            payload:error.response.data
            
        })
    }
}