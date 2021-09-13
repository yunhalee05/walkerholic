import React from 'react'
import { useState } from 'react'
import {Link} from 'react-router-dom'

function Header() {

    const [keyword, setKeyword] = useState('')

    return (
        <div className="header">
            <div className="header_logo">
                <i class="fas fa-walking"></i>
                <i class="fas fa-male"></i>
                walkerholic
            </div>

            <div className="header_search">
                <input type="text" value={keyword} onChange={e=>setKeyword(e.target.value)} />
                <i class="fas fa-eraser" style={keyword.length>0 ? {transform:'translateX(-20px)'}: {color:'white'}}></i>
                <i class="far fa-search"></i>
            </div>

            <div className="header_menu">
                <div className="header_content">
                    Discover
                </div>

                <div className="header_content">
                    <Link to='/signin'>Login</Link>
                </div>

                <div className="header_content">
                    <Link to='/user/1'>UserName</Link>
                </div>
            </div>
        </div>
    )
}

export default Header
