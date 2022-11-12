import {Button, Card, Divider} from "antd";
import {FormOutlined, SettingOutlined} from "@ant-design/icons";
import jump from "../../assets/jump.png"

type CompetitionProps = {
    competition_id: number,
    style?: React.CSSProperties,
    data?: any
}

const {Meta} = Card;

export const Competition = ({competition_id, style}: CompetitionProps) => {
    return (
        <Card style={style} actions={[
            <Button icon={<SettingOutlined key='competition-settings' title={'Editar competicion'}/>}
                    style={{color: "gray"}}
                    type="link">
                Administrar
            </Button>,
            <Button icon={<FormOutlined key='new-inscription' title={'Inscribirse'}/>}
                    style={{color: "gray"}}
                    type="link">
                Inscribirse
            </Button>
        ]}
              cover={<img style={{width: 64, marginLeft: 16, marginTop: 16}} alt="Competition" src={jump}/>}
        >
            <Meta title={'Competition ' + competition_id}
                  style={{width: '100%'}}
                  description={<>
                      <p><b>Location:</b>Location</p>
                      <p><b>Fecha:</b>11-10-2022</p>
                      <p><b>Hora:</b>10:00 am</p>
                  </>}
            />
        </Card>
    )
}