import React from 'react'
import { useState } from 'react'
import { useDispatch } from 'react-redux'
import {Link} from 'react-router-dom'
import { login } from '../_actions/AuthActions'

function LoginScreen() {

    const [email, setEmail] = useState('')
    const [password, setPassword] = useState('')

    const dispatch = useDispatch()
    const handleSubmit = (e)=>{
        e.preventDefault();

        dispatch(login({email, password}))
    }
    return (
        <div className="auth">
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
                    <input type="text" className="form_control" value={password} onChange={e=>setPassword(e.target.value)} />
                </div>

                <div className="form_button">
                    <button>Sign in</button>
                </div>

                <div className="form_switch">
                    <strong>Don't you have an account yet? <Link to="/signup">Register Now!</Link></strong>
                </div>
            </form>
            
        </div>
    )
}

export default LoginScreen
