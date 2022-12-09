import {Button, DatePicker, Divider, Form, Input, InputNumber, Steps} from "antd";
import {useState} from "react";
import TextArea from "antd/lib/input/TextArea";
import {CompetitionDTO} from "../../generated/api";

type CreateCompetitionProps = {
    data: undefined | CompetitionDTO;
}



export const CreateCompetitionForm = ({data}:CreateCompetitionProps) => {
    const [currentStep, setCurrentStep] = useState(0)
    const competitionData: CompetitionDTO = (data === undefined) ? {
        inscriptionLimitDate: (new Date()).toUTCString(),
        maxInscriptionsPerSwimmer: 0,
        reference: "",
        title: ""
    } : data;

    let items = [
        {
            title: 'Competition'
        },
        {
            title: 'Añadir session'
        }
    ]

    return <>
        <Steps current={currentStep} items={items}/>
        <Divider/>

        <Divider/>
        <div>
            <Button>
                Añadir sesión
            </Button>
            {/*{currentStep > 0 && (*/}
            {/*    <Button onClick={() => prev()}>*/}
            {/*        Anterior*/}
            {/*    </Button>*/}
            {/*)}*/}
            {/*{currentStep < (steps.length - 2) && (*/}
            {/*    <Button onClick={() => next()}>*/}
            {/*        Siguiente*/}
            {/*    </Button>*/}
            {/*)}*/}
        </div>
    </>
}

const CompetitionForm = () => {
    return <>
        <Form labelCol={{span: 5}} wrapperCol={{span: 19}} labelAlign={"left"}>
            <Form.Item label="Title">
                <Input.Group compact>
                    <Input style={{width: '70%'}} placeholder="Nombre de la competición" size="large"/>
                    <Input style={{width: '30%'}} placeholder="Referencia" size="large"/>
                </Input.Group>
            </Form.Item>
            <Form.Item label="Descripción:">
                <TextArea rows={4}/>
            </Form.Item>
            <Form.Item label="Dia de competición">
                <DatePicker/>
            </Form.Item>
            <Form.Item label="Día limite de inscripción">
                <DatePicker/>
            </Form.Item>
            <Form.Item label="Maximo de inscripciones por nadador">
                <InputNumber/>
            </Form.Item>
        </Form>
    </>
}

const SessionForm = () => {
    return <>
        <Form labelCol={{span: 5}} wrapperCol={{span: 19}} labelAlign={"left"}>
            <Form.Item label="Nombre de sesión">
                <Input placeholder="Sesión 1"/>
            </Form.Item>
        </Form>
        <Form labelCol={{span: 5}} wrapperCol={{span: 19}} labelAlign={"left"}>
            <Form.Item label="Fecha de sesión">
                <DatePicker/>
            </Form.Item>
        </Form>
    </>
}