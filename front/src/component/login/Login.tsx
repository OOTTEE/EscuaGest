import './Login.css'
import {Button, Col, Form, Row} from "antd";
import {Content} from "antd/lib/layout/layout";
import {AuthService} from "../../infrastructure/authentication/AuthService";
import {useNavigate} from "react-router-dom";


export const Login = () => {
    const authService = new AuthService();
    const navigate = useNavigate()

    authService.isLoggedIn()
        .then(value => {
            if(value) {
                navigate('/')
            }
        })

    return (
        <>
            <Row justify="center" align="middle" style={{minHeight: '100vh'}}>
                <Col
                    xs={{span: 20}}
                    sm={{span: 10}}
                    md={{span: 8}}
                    lg={{span: 6}}
                >
                    <Content className="form-container">
                        <Form
                            name="login"
                            className="login-form"
                            onFinish={values => {
                                authService.signinPopup()
                                    .then(value => {
                                        navigate('/')
                                    })
                            }}
                        >
                            <Form.Item>
                                <Button type="primary" htmlType="submit" className="login-form-button">
                                    Log in
                                </Button>
                                <a className="login-form-forgot" href="/Users/ote/EscuaGest/front/public">
                                    Forgot password
                                </a>
                            </Form.Item>
                        </Form>
                    </Content>
                </Col>
            </Row>
        </>
    )
}