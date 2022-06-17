import { AUTH_FAIL, AUTH_REQUEST, AUTH_SUCCESS, LOGIN_FAIL, LOGIN_REQUEST, LOGIN_SUCCESS, LOGOUT_FAIL, LOGOUT_REQUEST, LOGOUT_SUCCESS, REGISTER_FAIL, REGISTER_REQUEST, REGISTER_SUCCESS } from "../_constants/AuthConstants"
import axios from "axios"

export const register = (userRequest, imageUrl) =>async(dispatch, getState)=>{

    dispatch({
        type:REGISTER_REQUEST
    })

    try{
        if( (imageUrl !== '') || (imageUrl != null)) {
            const bodyFormData = new FormData()
            bodyFormData.append('multipartFile', imageUrl)
            await axios.post('/users/images', bodyFormData).then(r =>{
                userRequest = {...userRequest, imageUrl:r.data}
            })
        }
        const res = await axios.post('/users', userRequest)

        dispatch({
            type:REGISTER_SUCCESS,
            payload:res.data
        })

        localStorage.setItem("walkerholic_token", JSON.stringify(res.data.token))

        return res.data.user.id

    }catch(error){
        dispatch({
            type:REGISTER_FAIL,
            payload: error.response && error.response.data
            ? error.response.data
            : error.message            
        })
    }
}

export const login = ({email, password}) =>async(dispatch, getState)=>{

    dispatch({
        type:LOGIN_REQUEST
    })

    const body = {
        username:email,
        password:password
    }

    try{
        const res = await axios.post('/sign-in', body)

        dispatch({
            type:LOGIN_SUCCESS,
            payload:res.data
        })

        localStorage.setItem("walkerholic_token", JSON.stringify(res.data.token).replace(/\"/gi, ""))

        return res.data

    }catch(error){
        dispatch({
            type:LOGIN_FAIL,
            payload: error.response && error.response.data
            ? error.response.data
            : error.message            
        })
    }
}


export const authenticate = (token) =>async(dispatch, getState)=>{

    dispatch({
        type:AUTH_REQUEST
    })


    try{
        const res = await axios.post(`/sign-in?token=${token}`,null)

        dispatch({
            type:AUTH_SUCCESS,
            payload:res.data
        })

        return res.data.user.id


    }catch(error){

        // dispatch({
        //     type:AUTH_FAIL,
        //     payload: error.response && error.response.data
        //     ? error.response.data
        //     : error.message            
        // })
        localStorage.removeItem("walkerholic_token")

    }
}


export const logout = () =>async(dispatch, getState)=>{

    dispatch({
        type:LOGOUT_REQUEST
    })

    try{
        dispatch({
            type:LOGOUT_SUCCESS
        })

        localStorage.removeItem("walkerholic_token")

    }catch(error){
        dispatch({
            type:LOGOUT_FAIL,
            payload: error.response && error.response.data
            ? error.response.data
            : error.message            
        })
    }
}