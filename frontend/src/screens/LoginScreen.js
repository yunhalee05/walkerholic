import axios from 'axios'
import React from 'react'
import { useState } from 'react'
import { useDispatch } from 'react-redux'
import {Link} from 'react-router-dom'
import { login } from '../_actions/AuthActions'
import { GET_AUTH_FOLLOWS } from '../_constants/AuthConstants'
import { GOOGLE_AUTH_URL, FACEBOOK_AUTH_URL, NAVER_AUTH_URL, KAKAO_AUTH_URL } from '../utils/SocialLogin';
import kakao from '../images/kakao.png'
import google from '../images/google.png'
import naver from '../images/naver.png'

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

                <div className="form_switch" style={{marginTop:"1rem"}}>
                    <div style={{width:"40%"}}><Link to="/signup">Create Account</Link></div>
                    <div>&nbsp; &nbsp; | &nbsp; &nbsp;</div>
                    <div style={{width:"40%"}}><Link to="/forgotPassword">Forgot Password</Link></div>
                </div>

                <div className="form_button" style={{margin:"2rem"}}>
                    <button>Sign in</button>
                </div>


                <div className="auth_oauth_button">
                    <a href={GOOGLE_AUTH_URL}><img src={google} alt="" /></a>
                    <a href={KAKAO_AUTH_URL}><img src={kakao} alt="" /></a>
                    <a href={NAVER_AUTH_URL}><img src={naver} alt="" /></a>
                </div>
            </form>
            
        </div>
    )
}

export default LoginScreen
