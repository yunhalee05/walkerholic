import axios from "axios"
import { CREATE_PRODUCT_FAIL, CREATE_PRODUCT_REQUEST, CREATE_PRODUCT_SUCCESS, DELETE_PRODUCT_FAIL, DELETE_PRODUCT_REQUEST, DELETE_PRODUCT_SUCCESS, EDIT_PRODUCT_FAIL, EDIT_PRODUCT_REQUEST, EDIT_PRODUCT_SUCCESS, GET_PRODUCTS_FAIL, GET_PRODUCTS_REQUEST, GET_PRODUCTS_SUCCESS, GET_PRODUCT_FAIL, GET_PRODUCT_LIST_FAIL, GET_PRODUCT_LIST_REQUEST, GET_PRODUCT_LIST_SUCCESS, GET_PRODUCT_REQUEST, GET_PRODUCT_SUCCESS, GET_SELLER_PRODUCTS_FAIL, GET_SELLER_PRODUCTS_REQUEST, GET_SELLER_PRODUCTS_SUCCESS } from "../_constants/ProductConstants"

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

export const getSellerProducts = (id,page, sort, category,keyword) =>async(dispatch, getState)=>{

    dispatch({
        type:GET_SELLER_PRODUCTS_REQUEST
    })

    const param = {
        sort : sort? sort :'',
        category : category ? category :'',
        keyword : keyword ? keyword : ''
    }

    try{
        const res = await axios.get(`/products/seller/${id}/${page}`,{params:param})

        dispatch({
            type:GET_SELLER_PRODUCTS_SUCCESS,
            payload:res.data
        })

    }catch(error){
        dispatch({
            type:GET_SELLER_PRODUCTS_FAIL,
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
export const getProductList = (page, sort) =>async(dispatch, getState)=>{

    dispatch({
        type:GET_PRODUCT_LIST_REQUEST
    })

    try{
        const res = await axios.get(`/productlist/${page}/${sort}`)

        dispatch({
            type:GET_PRODUCT_LIST_SUCCESS,
            payload:res.data
        })


    }catch(error){
        dispatch({
            type:GET_PRODUCT_LIST_FAIL,
            payload:error.response.data
            
        })
    }
}
export const getSellerProductList = (page, sort, id) =>async(dispatch, getState)=>{

    dispatch({
        type:GET_PRODUCT_LIST_REQUEST
    })

    try{
        const res = await axios.get(`/productlist/${page}/${sort}/${id}`)

        dispatch({
            type:GET_PRODUCT_LIST_SUCCESS,
            payload:res.data
        })


    }catch(error){
        dispatch({
            type:GET_PRODUCT_LIST_FAIL,
            payload:error.response.data
            
        })
    }
}
// export const deleteProduct = (id) =>async(dispatch, getState)=>{

//     dispatch({
//         type:DELETE_PRODUCT_REQUEST
//     })

//     try{
//         const res = await axios.delete(`/product/delete/${id}`)

//         dispatch({
//             type:DELETE_PRODUCT_SUCCESS,
//             payload:res.data
//         })


//     }catch(error){
//         dispatch({
//             type:DELETE_PRODUCT_FAIL,
//             payload:error.response.data
            
//         })
//     }
// }
export const editProduct = (bodyFormData) =>async(dispatch, getState)=>{

    dispatch({
        type:EDIT_PRODUCT_REQUEST
    })

    try{
        const res = await axios.post('/product/save',bodyFormData)

        dispatch({
            type:EDIT_PRODUCT_SUCCESS,
            payload:res.data
        })


    }catch(error){
        dispatch({
            type:EDIT_PRODUCT_FAIL,
            payload:error.response.data
            
        })
    }
}

export const createProduct = (bodyFormData) =>async(dispatch, getState)=>{

    dispatch({
        type:CREATE_PRODUCT_REQUEST
    })

    try{
        const res = await axios.post('/product/save',bodyFormData)

        dispatch({
            type:CREATE_PRODUCT_SUCCESS,
            payload:res.data
        })


    }catch(error){
        dispatch({
            type:CREATE_PRODUCT_FAIL,
            payload:error.response.data
            
        })
    }
}

