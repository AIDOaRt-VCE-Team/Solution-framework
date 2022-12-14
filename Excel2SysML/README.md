# Excel to SysML

This folder contain a model transformation to be used to import engineering data from (legacy) structured documents like Excel spreadsheets. 


- The input model is an Excel spreadsheet containing variability information of CPS components used in the [Dumper System](https://www.volvoce.com/europe/en/products/articulated-haulers/a60h/) by Volvo Construction Equipments.
- The output model is a SysML v1.6 model (i.e., a UML model annotated with the SysML profile). The SysML model is further extended with annotations from the [AutomationML Profile](https://github.com/AIDOaRt-VCE-Team/ecmfa-2023/tree/main/AutomationML) and [VCE profile](https://github.com/AIDOaRt-VCE-Team/ecmfa-2023/tree/main/VCE_Profile).
- Tools: The model transformation is implemented in [Epsilon](https://www.eclipse.org/epsilon/doc/articles/excel/)[https://www.eclipse.org/epsilon/doc/articles/excel/].
The output model can be opened in [Papyrus UML modeling environment](https://www.eclipse.org/papyrus/).
