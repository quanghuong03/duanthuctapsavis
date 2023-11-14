import React from 'react';
import {Form, Input} from "antd";
import {Link} from "react-router-dom";
import {adminAuthService} from "../../../services/admin";
import {toastService} from "../../../services/common";
import {useNavigateOrRedirectUrl} from "../../../hook";
import { userAuthService } from '../../../services/user';

function UserRegister(props) {

    const [setPage] = useNavigateOrRedirectUrl();

    const registerHandle = async (form) => {
        try {
            const registerRes = await  adminAuthService.register(form);
            console.log(registerRes)
            toastService.success('Register Successfully')
            userAuthService.saveAuthInfo(registerRes.data);
            setPage('/');
        } catch (e) {
            toastService.error(e.apiMessage)
        }
    }

    return (
        <div className="login-page">
            <div className="breadcrumb-section">
                <div className="container">
                    <h2>CREATE ACCOUNT</h2>
                </div>
            </div>
            <div className="container login-form mt-3">
                <div className="page-info">
                </div>
                <h3>CREATE ACCOUNT</h3>
                <Form className="theme-form" layout="vertical" onFinish={registerHandle}>
                    <div className="theme-card">
                        <div className={'row'}>
                            <div className="col-6">

                                <Form.Item name={'name'} label='Username'
                                           rules={[{required: true, message: 'Username is required'}]}>
                                    <Input placeholder="email/username" size="large"/>
                                </Form.Item>
                                <Form.Item name={'password'} label='Password'
                                           rules={[{required: true, message: 'Password is required'}]}>
                                    <Input placeholder="password" type="password" size="large"/>
                                </Form.Item>
                                <button type="submit" className="btn btn-solid">Register</button>
                            </div>
                            <div className="col-6">
                                <Form.Item name={'email'} label='Email'
                                           rules={[{required: true, message: 'Email is required'}]}>
                                    <Input placeholder="email" size="large"/>
                                </Form.Item>
                                <Form.Item name={'phone'} label='Phone'
                                           rules={[{required: true, message: 'Phone is required'}]}>
                                    <Input placeholder="phone" size="large"/>
                                </Form.Item>
                            </div>
                        </div>
                    </div>
                </Form>
            </div>
        </div>
    );
}

export default UserRegister;