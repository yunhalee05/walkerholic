import axios from 'axios'
import React from 'react'
import { useState } from 'react'
import { useDispatch } from 'react-redux'
import {Link} from 'react-router-dom'
import { login } from '../_actions/AuthActions'
import { GET_AUTH_FOLLOWS } from '../_constants/AuthConstants'
import { GOOGLE_AUTH_URL, FACEBOOK_AUTH_URL, NAVER_AUTH_URL, KAKAO_AUTH_URL, ACCESS_TOKEN } from '../utils/SocialLogin';


function LoginScreen(props) {

    const [email, setEmail] = useState('')
    const [password, setPassword] = useState('')
    const [typePass, setTypePass] = useState(false)

    const dispatch = useDispatch()
    const handleSubmit = (e)=>{
        e.preventDefault();

        dispatch(login({email, password})).then(async(id)=>{
            const res = await axios.get(`/follows/${id}`)
            dispatch({
              type:GET_AUTH_FOLLOWS,
              payload:res.data
            })
            props.history.push('/')
        })
        
    }

    return (
        <div className="auth" style={{marginTop:"20%"}}>
            <form onSubmit={handleSubmit}>
                <div className="auth_message">
                    Welcome to walkerholic!
                </div>
                <button><a href="http://localhost:8080/oauth2/authorization/google">Continue with Google</a></button>
                <div className="form_group">
                    <label htmlFor="email">Email</label>
                    <input type="email" className="form_control" value={email} onChange={e=>setEmail(e.target.value)}/>
                </div>

                <div className="form_group">
                    <label htmlFor="password">Password</label>
                    <input type={typePass ? "text" :"password"} className="form_control" value={password} onChange={e=>setPassword(e.target.value)} />
                    <small className="pass" onClick={()=>setTypePass(!typePass)}>
                        {typePass ? 'Hide' : 'Show'}
                    </small>
                </div>

                <div className="form_button" style={{margin:"2rem"}}>
                    <button>Sign in</button>
                </div>

                <div className="form_switch">
                    <div>Don't you have an account yet? </div>
                    <div><Link to="/signup">Register Now!</Link></div>
                </div>
            </form>
            
        </div>
    )
}

export default LoginScreen
