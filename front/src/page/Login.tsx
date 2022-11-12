import './Login.css'
import {Button, Col, Form, Input, Row} from "antd";
import {KeyOutlined, UserOutlined} from "@ant-design/icons";
import {Content} from "antd/lib/layout/layout";

export const Login = () => {
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
                        >
                            <Form.Item
                                name="username"
                                rules={[{required: true, message: 'Porfavor introduce tu Usuario!'}]}
                            >
                                <Input prefix={<UserOutlined></UserOutlined>}
                                       placeholder="Usuario"
                                />
                            </Form.Item>
                            <Form.Item
                                name="password"
                                rules={[{required: true, message: 'Porfavor introduce tu Contraseña!'}]}
                            >
                                <Input prefix={<KeyOutlined></KeyOutlined>}
                                       placeholder="Contraseña"
                                />
                            </Form.Item>
                            {/*<Form.Item>*/}
                            {/*    <Form.Item name="remember" valuePropName="checked" noStyle>*/}
                            {/*        <Checkbox>Remember me</Checkbox>*/}
                            {/*    </Form.Item>*/}
                            <Form.Item>
                                <Button type="primary" htmlType="submit" className="login-form-button">
                                    Log in
                                </Button>
                                <a className="login-form-forgot" href="">
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
