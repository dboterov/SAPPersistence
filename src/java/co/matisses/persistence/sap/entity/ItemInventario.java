package co.matisses.persistence.sap.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Objects;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author dbotero
 */
@Entity
@Table(name = "OITM")
@NamedQueries({
    @NamedQuery(name = "ItemInventario.findAll", query = "SELECT i FROM ItemInventario i")})
@XmlRootElement
public class ItemInventario implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "ItemCode")
    private String itemCode;
    @Size(max = 100)
    @Column(name = "ItemName")
    private String itemName;
    @Size(max = 100)
    @Column(name = "FrgnName")
    private String frgnName;
    @Column(name = "ItmsGrpCod")
    private Short itmsGrpCod;
    @Column(name = "CstGrpCode")
    private Short cstGrpCode;
    @Size(max = 8)
    @Column(name = "VatGourpSa")
    private String vatGourpSa;
    @Size(max = 254)
    @Column(name = "CodeBars")
    private String codeBars;
    @Column(name = "VATLiable")
    private Character vATLiable;
    @Column(name = "PrchseItem")
    private Character prchseItem;
    @Column(name = "SellItem")
    private Character sellItem;
    @Column(name = "InvntItem")
    private Character invntItem;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "OnHand")
    private BigDecimal onHand;
    @Column(name = "IsCommited")
    private BigDecimal isCommited;
    @Column(name = "OnOrder")
    private BigDecimal onOrder;
    @Size(max = 15)
    @Column(name = "IncomeAcct")
    private String incomeAcct;
    @Size(max = 15)
    @Column(name = "ExmptIncom")
    private String exmptIncom;
    @Column(name = "MaxLevel")
    private BigDecimal maxLevel;
    @Size(max = 8)
    @Column(name = "DfltWH")
    private String dfltWH;
    @Size(max = 15)
    @Column(name = "CardCode")
    private String cardCode;
    @Size(max = 17)
    @Column(name = "SuppCatNum")
    private String suppCatNum;
    @Size(max = 100)
    @Column(name = "BuyUnitMsr")
    private String buyUnitMsr;
    @Column(name = "NumInBuy")
    private BigDecimal numInBuy;
    @Column(name = "ReorderQty")
    private BigDecimal reorderQty;
    @Column(name = "MinLevel")
    private BigDecimal minLevel;
    @Column(name = "LstEvlPric")
    private BigDecimal lstEvlPric;
    @Column(name = "LstEvlDate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date lstEvlDate;
    @Column(name = "CustomPer")
    private BigDecimal customPer;
    @Column(name = "Canceled")
    private Character canceled;
    @Column(name = "MnufctTime")
    private Integer mnufctTime;
    @Column(name = "WholSlsTax")
    private Character wholSlsTax;
    @Column(name = "RetilrTax")
    private Character retilrTax;
    @Column(name = "SpcialDisc")
    private BigDecimal spcialDisc;
    @Column(name = "DscountCod")
    private Short dscountCod;
    @Column(name = "TrackSales")
    private Character trackSales;
    @Size(max = 100)
    @Column(name = "SalUnitMsr")
    private String salUnitMsr;
    @Column(name = "NumInSale")
    private BigDecimal numInSale;
    @Column(name = "Consig")
    private BigDecimal consig;
    @Column(name = "QueryGroup")
    private Integer queryGroup;
    @Column(name = "Counted")
    private BigDecimal counted;
    @Column(name = "OpenBlnc")
    private BigDecimal openBlnc;
    @Column(name = "EvalSystem")
    private Character evalSystem;
    @Column(name = "UserSign")
    private Short userSign;
    @Column(name = "FREE")
    private Character free;
    @Size(max = 200)
    @Column(name = "PicturName")
    private String picturName;
    @Column(name = "Transfered")
    private Character transfered;
    @Column(name = "BlncTrnsfr")
    private Character blncTrnsfr;
    @Size(max = 1073741823)
    @Column(name = "UserText")
    private String userText;
    @Size(max = 17)
    @Column(name = "SerialNum")
    private String serialNum;
    @Column(name = "CommisPcnt")
    private BigDecimal commisPcnt;
    @Column(name = "CommisSum")
    private BigDecimal commisSum;
    @Column(name = "CommisGrp")
    private Short commisGrp;
    @Column(name = "TreeType")
    private Character treeType;
    @Column(name = "TreeQty")
    private BigDecimal treeQty;
    @Column(name = "LastPurPrc")
    private BigDecimal lastPurPrc;
    @Size(max = 3)
    @Column(name = "LastPurCur")
    private String lastPurCur;
    @Column(name = "LastPurDat")
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastPurDat;
    @Size(max = 3)
    @Column(name = "ExitCur")
    private String exitCur;
    @Column(name = "ExitPrice")
    private BigDecimal exitPrice;
    @Size(max = 8)
    @Column(name = "ExitWH")
    private String exitWH;
    @Column(name = "AssetItem")
    private Character assetItem;
    @Column(name = "WasCounted")
    private Character wasCounted;
    @Column(name = "ManSerNum")
    private Character manSerNum;
    @Column(name = "SHeight1")
    private BigDecimal sHeight1;
    @Column(name = "SHght1Unit")
    private Short sHght1Unit;
    @Column(name = "SHeight2")
    private BigDecimal sHeight2;
    @Column(name = "SHght2Unit")
    private Short sHght2Unit;
    @Column(name = "SWidth1")
    private BigDecimal sWidth1;
    @Column(name = "SWdth1Unit")
    private Short sWdth1Unit;
    @Column(name = "SWidth2")
    private BigDecimal sWidth2;
    @Column(name = "SWdth2Unit")
    private Short sWdth2Unit;
    @Column(name = "SLength1")
    private BigDecimal sLength1;
    @Column(name = "SLen1Unit")
    private Short sLen1Unit;
    @Column(name = "Slength2")
    private BigDecimal slength2;
    @Column(name = "SLen2Unit")
    private Short sLen2Unit;
    @Column(name = "SVolume")
    private BigDecimal sVolume;
    @Column(name = "SVolUnit")
    private Short sVolUnit;
    @Column(name = "SWeight1")
    private BigDecimal sWeight1;
    @Column(name = "SWght1Unit")
    private Short sWght1Unit;
    @Column(name = "SWeight2")
    private BigDecimal sWeight2;
    @Column(name = "SWght2Unit")
    private Short sWght2Unit;
    @Column(name = "BHeight1")
    private BigDecimal bHeight1;
    @Column(name = "BHght1Unit")
    private Short bHght1Unit;
    @Column(name = "BHeight2")
    private BigDecimal bHeight2;
    @Column(name = "BHght2Unit")
    private Short bHght2Unit;
    @Column(name = "BWidth1")
    private BigDecimal bWidth1;
    @Column(name = "BWdth1Unit")
    private Short bWdth1Unit;
    @Column(name = "BWidth2")
    private BigDecimal bWidth2;
    @Column(name = "BWdth2Unit")
    private Short bWdth2Unit;
    @Column(name = "BLength1")
    private BigDecimal bLength1;
    @Column(name = "BLen1Unit")
    private Short bLen1Unit;
    @Column(name = "Blength2")
    private BigDecimal blength2;
    @Column(name = "BLen2Unit")
    private Short bLen2Unit;
    @Column(name = "BVolume")
    private BigDecimal bVolume;
    @Column(name = "BVolUnit")
    private Short bVolUnit;
    @Column(name = "BWeight1")
    private BigDecimal bWeight1;
    @Column(name = "BWght1Unit")
    private Short bWght1Unit;
    @Column(name = "BWeight2")
    private BigDecimal bWeight2;
    @Column(name = "BWght2Unit")
    private Short bWght2Unit;
    @Size(max = 3)
    @Column(name = "FixCurrCms")
    private String fixCurrCms;
    @Column(name = "FirmCode")
    private Short firmCode;
    @Column(name = "LstSalDate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date lstSalDate;
    @Column(name = "QryGroup1")
    private Character qryGroup1;
    @Column(name = "QryGroup2")
    private Character qryGroup2;
    @Column(name = "QryGroup3")
    private Character qryGroup3;
    @Column(name = "QryGroup4")
    private Character qryGroup4;
    @Column(name = "QryGroup5")
    private Character qryGroup5;
    @Column(name = "QryGroup6")
    private Character qryGroup6;
    @Column(name = "QryGroup7")
    private Character qryGroup7;
    @Column(name = "QryGroup8")
    private Character qryGroup8;
    @Column(name = "QryGroup9")
    private Character qryGroup9;
    @Column(name = "QryGroup10")
    private Character qryGroup10;
    @Column(name = "QryGroup11")
    private Character qryGroup11;
    @Column(name = "QryGroup12")
    private Character qryGroup12;
    @Column(name = "QryGroup13")
    private Character qryGroup13;
    @Column(name = "QryGroup14")
    private Character qryGroup14;
    @Column(name = "QryGroup15")
    private Character qryGroup15;
    @Column(name = "QryGroup16")
    private Character qryGroup16;
    @Column(name = "QryGroup17")
    private Character qryGroup17;
    @Column(name = "QryGroup18")
    private Character qryGroup18;
    @Column(name = "QryGroup19")
    private Character qryGroup19;
    @Column(name = "QryGroup20")
    private Character qryGroup20;
    @Column(name = "QryGroup21")
    private Character qryGroup21;
    @Column(name = "QryGroup22")
    private Character qryGroup22;
    @Column(name = "QryGroup23")
    private Character qryGroup23;
    @Column(name = "QryGroup24")
    private Character qryGroup24;
    @Column(name = "QryGroup25")
    private Character qryGroup25;
    @Column(name = "QryGroup26")
    private Character qryGroup26;
    @Column(name = "QryGroup27")
    private Character qryGroup27;
    @Column(name = "QryGroup28")
    private Character qryGroup28;
    @Column(name = "QryGroup29")
    private Character qryGroup29;
    @Column(name = "QryGroup30")
    private Character qryGroup30;
    @Column(name = "QryGroup31")
    private Character qryGroup31;
    @Column(name = "QryGroup32")
    private Character qryGroup32;
    @Column(name = "QryGroup33")
    private Character qryGroup33;
    @Column(name = "QryGroup34")
    private Character qryGroup34;
    @Column(name = "QryGroup35")
    private Character qryGroup35;
    @Column(name = "QryGroup36")
    private Character qryGroup36;
    @Column(name = "QryGroup37")
    private Character qryGroup37;
    @Column(name = "QryGroup38")
    private Character qryGroup38;
    @Column(name = "QryGroup39")
    private Character qryGroup39;
    @Column(name = "QryGroup40")
    private Character qryGroup40;
    @Column(name = "QryGroup41")
    private Character qryGroup41;
    @Column(name = "QryGroup42")
    private Character qryGroup42;
    @Column(name = "QryGroup43")
    private Character qryGroup43;
    @Column(name = "QryGroup44")
    private Character qryGroup44;
    @Column(name = "QryGroup45")
    private Character qryGroup45;
    @Column(name = "QryGroup46")
    private Character qryGroup46;
    @Column(name = "QryGroup47")
    private Character qryGroup47;
    @Column(name = "QryGroup48")
    private Character qryGroup48;
    @Column(name = "QryGroup49")
    private Character qryGroup49;
    @Column(name = "QryGroup50")
    private Character qryGroup50;
    @Column(name = "QryGroup51")
    private Character qryGroup51;
    @Column(name = "QryGroup52")
    private Character qryGroup52;
    @Column(name = "QryGroup53")
    private Character qryGroup53;
    @Column(name = "QryGroup54")
    private Character qryGroup54;
    @Column(name = "QryGroup55")
    private Character qryGroup55;
    @Column(name = "QryGroup56")
    private Character qryGroup56;
    @Column(name = "QryGroup57")
    private Character qryGroup57;
    @Column(name = "QryGroup58")
    private Character qryGroup58;
    @Column(name = "QryGroup59")
    private Character qryGroup59;
    @Column(name = "QryGroup60")
    private Character qryGroup60;
    @Column(name = "QryGroup61")
    private Character qryGroup61;
    @Column(name = "QryGroup62")
    private Character qryGroup62;
    @Column(name = "QryGroup63")
    private Character qryGroup63;
    @Column(name = "QryGroup64")
    private Character qryGroup64;
    @Column(name = "CreateDate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createDate;
    @Column(name = "UpdateDate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date updateDate;
    @Size(max = 20)
    @Column(name = "ExportCode")
    private String exportCode;
    @Column(name = "SalFactor1")
    private BigDecimal salFactor1;
    @Column(name = "SalFactor2")
    private BigDecimal salFactor2;
    @Column(name = "SalFactor3")
    private BigDecimal salFactor3;
    @Column(name = "SalFactor4")
    private BigDecimal salFactor4;
    @Column(name = "PurFactor1")
    private BigDecimal purFactor1;
    @Column(name = "PurFactor2")
    private BigDecimal purFactor2;
    @Column(name = "PurFactor3")
    private BigDecimal purFactor3;
    @Column(name = "PurFactor4")
    private BigDecimal purFactor4;
    @Size(max = 40)
    @Column(name = "SalFormula")
    private String salFormula;
    @Size(max = 40)
    @Column(name = "PurFormula")
    private String purFormula;
    @Size(max = 8)
    @Column(name = "VatGroupPu")
    private String vatGroupPu;
    @Column(name = "AvgPrice")
    private BigDecimal avgPrice;
    @Size(max = 30)
    @Column(name = "PurPackMsr")
    private String purPackMsr;
    @Column(name = "PurPackUn")
    private BigDecimal purPackUn;
    @Size(max = 30)
    @Column(name = "SalPackMsr")
    private String salPackMsr;
    @Column(name = "SalPackUn")
    private BigDecimal salPackUn;
    @Column(name = "SCNCounter")
    private Short sCNCounter;
    @Column(name = "ManBtchNum")
    private Character manBtchNum;
    @Column(name = "ManOutOnly")
    private Character manOutOnly;
    @Column(name = "DataSource")
    private Character dataSource;
    @Column(name = "validFor")
    private Character validFor;
    @Column(name = "validFrom")
    @Temporal(TemporalType.TIMESTAMP)
    private Date validFrom;
    @Column(name = "validTo")
    @Temporal(TemporalType.TIMESTAMP)
    private Date validTo;
    @Column(name = "frozenFor")
    private Character frozenFor;
    @Column(name = "frozenFrom")
    @Temporal(TemporalType.TIMESTAMP)
    private Date frozenFrom;
    @Column(name = "frozenTo")
    @Temporal(TemporalType.TIMESTAMP)
    private Date frozenTo;
    @Column(name = "BlockOut")
    private Character blockOut;
    @Size(max = 30)
    @Column(name = "ValidComm")
    private String validComm;
    @Size(max = 30)
    @Column(name = "FrozenComm")
    private String frozenComm;
    @Column(name = "LogInstanc")
    private Integer logInstanc;
    @Size(max = 20)
    @Column(name = "ObjType")
    private String objType;
    @Size(max = 16)
    @Column(name = "SWW")
    private String sww;
    @Column(name = "Deleted")
    private Character deleted;
    @Column(name = "DocEntry")
    private Integer docEntry;
    @Size(max = 15)
    @Column(name = "ExpensAcct")
    private String expensAcct;
    @Size(max = 15)
    @Column(name = "FrgnInAcct")
    private String frgnInAcct;
    @Column(name = "ShipType")
    private Short shipType;
    @Column(name = "GLMethod")
    private Character gLMethod;
    @Size(max = 15)
    @Column(name = "ECInAcct")
    private String eCInAcct;
    @Size(max = 15)
    @Column(name = "FrgnExpAcc")
    private String frgnExpAcc;
    @Size(max = 15)
    @Column(name = "ECExpAcc")
    private String eCExpAcc;
    @Column(name = "TaxType")
    private Character taxType;
    @Column(name = "ByWh")
    private Character byWh;
    @Column(name = "WTLiable")
    private Character wTLiable;
    @Column(name = "ItemType")
    private Character itemType;
    @Size(max = 20)
    @Column(name = "WarrntTmpl")
    private String warrntTmpl;
    @Size(max = 20)
    @Column(name = "BaseUnit")
    private String baseUnit;
    @Size(max = 3)
    @Column(name = "CountryOrg")
    private String countryOrg;
    @Column(name = "StockValue")
    private BigDecimal stockValue;
    @Column(name = "Phantom")
    private Character phantom;
    @Column(name = "IssueMthd")
    private Character issueMthd;
    @Column(name = "FREE1")
    private Character free1;
    @Column(name = "PricingPrc")
    private BigDecimal pricingPrc;
    @Column(name = "MngMethod")
    private Character mngMethod;
    @Column(name = "ReorderPnt")
    private BigDecimal reorderPnt;
    @Size(max = 100)
    @Column(name = "InvntryUom")
    private String invntryUom;
    @Column(name = "PlaningSys")
    private Character planingSys;
    @Column(name = "PrcrmntMtd")
    private Character prcrmntMtd;
    @Column(name = "OrdrIntrvl")
    private Short ordrIntrvl;
    @Column(name = "OrdrMulti")
    private BigDecimal ordrMulti;
    @Column(name = "MinOrdrQty")
    private BigDecimal minOrdrQty;
    @Column(name = "LeadTime")
    private Integer leadTime;
    @Column(name = "IndirctTax")
    private Character indirctTax;
    @Size(max = 8)
    @Column(name = "TaxCodeAR")
    private String taxCodeAR;
    @Size(max = 8)
    @Column(name = "TaxCodeAP")
    private String taxCodeAP;
    @Column(name = "OSvcCode")
    private Integer oSvcCode;
    @Column(name = "ISvcCode")
    private Integer iSvcCode;
    @Column(name = "ServiceGrp")
    private Integer serviceGrp;
    @Column(name = "NCMCode")
    private Integer nCMCode;
    @Size(max = 3)
    @Column(name = "MatType")
    private String matType;
    @Column(name = "MatGrp")
    private Integer matGrp;
    @Size(max = 2)
    @Column(name = "ProductSrc")
    private String productSrc;
    @Column(name = "ServiceCtg")
    private Integer serviceCtg;
    @Column(name = "ItemClass")
    private Character itemClass;
    @Column(name = "Excisable")
    private Character excisable;
    @Column(name = "ChapterID")
    private Integer chapterID;
    @Size(max = 40)
    @Column(name = "NotifyASN")
    private String notifyASN;
    @Size(max = 20)
    @Column(name = "ProAssNum")
    private String proAssNum;
    @Column(name = "AssblValue")
    private BigDecimal assblValue;
    @Column(name = "DNFEntry")
    private Integer dNFEntry;
    @Column(name = "UserSign2")
    private Short userSign2;
    @Size(max = 30)
    @Column(name = "Spec")
    private String spec;
    @Size(max = 4)
    @Column(name = "TaxCtg")
    private String taxCtg;
    @Column(name = "Series")
    private Short series;
    @Column(name = "Number")
    private Integer number;
    @Column(name = "FuelCode")
    private Integer fuelCode;
    @Size(max = 2)
    @Column(name = "BeverTblC")
    private String beverTblC;
    @Size(max = 2)
    @Column(name = "BeverGrpC")
    private String beverGrpC;
    @Column(name = "BeverTM")
    private Integer beverTM;
    @Size(max = 1073741823)
    @Column(name = "Attachment")
    private String attachment;
    @Column(name = "AtcEntry")
    private Integer atcEntry;
    @Column(name = "ToleranDay")
    private Integer toleranDay;
    @Column(name = "UgpEntry")
    private Integer ugpEntry;
    @Column(name = "PUoMEntry")
    private Integer pUoMEntry;
    @Column(name = "SUoMEntry")
    private Integer sUoMEntry;
    @Column(name = "IUoMEntry")
    private Integer iUoMEntry;
    @Column(name = "IssuePriBy")
    private Short issuePriBy;
    @Size(max = 20)
    @Column(name = "AssetClass")
    private String assetClass;
    @Size(max = 15)
    @Column(name = "AssetGroup")
    private String assetGroup;
    @Size(max = 12)
    @Column(name = "InventryNo")
    private String inventryNo;
    @Column(name = "Technician")
    private Integer technician;
    @Column(name = "Employee")
    private Integer employee;
    @Column(name = "Location")
    private Integer location;
    @Column(name = "StatAsset")
    private Character statAsset;
    @Column(name = "Cession")
    private Character cession;
    @Column(name = "DeacAftUL")
    private Character deacAftUL;
    @Column(name = "AsstStatus")
    private Character asstStatus;
    @Column(name = "CapDate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date capDate;
    @Column(name = "AcqDate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date acqDate;
    @Column(name = "RetDate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date retDate;
    @Column(name = "GLPickMeth")
    private Character gLPickMeth;
    @Column(name = "NoDiscount")
    private Character noDiscount;
    @Column(name = "MgrByQty")
    private Character mgrByQty;
    @Size(max = 100)
    @Column(name = "AssetRmk1")
    private String assetRmk1;
    @Size(max = 100)
    @Column(name = "AssetRmk2")
    private String assetRmk2;
    @Column(name = "AssetAmnt1")
    private BigDecimal assetAmnt1;
    @Column(name = "AssetAmnt2")
    private BigDecimal assetAmnt2;
    @Size(max = 15)
    @Column(name = "DeprGroup")
    private String deprGroup;
    @Size(max = 30)
    @Column(name = "AssetSerNo")
    private String assetSerNo;
    @Size(max = 100)
    @Column(name = "CntUnitMsr")
    private String cntUnitMsr;
    @Column(name = "NumInCnt")
    private BigDecimal numInCnt;
    @Column(name = "INUoMEntry")
    private Integer iNUoMEntry;
    @Column(name = "OneBOneRec")
    private Character oneBOneRec;
    @Size(max = 2)
    @Column(name = "RuleCode")
    private String ruleCode;
    @Size(max = 10)
    @Column(name = "ScsCode")
    private String scsCode;
    @Size(max = 2)
    @Column(name = "SpProdType")
    private String spProdType;
    @Column(name = "IWeight1")
    private BigDecimal iWeight1;
    @Column(name = "IWght1Unit")
    private Short iWght1Unit;
    @Column(name = "IWeight2")
    private BigDecimal iWeight2;
    @Column(name = "IWght2Unit")
    private Short iWght2Unit;
    @Column(name = "CompoWH")
    private Character compoWH;
    @Column(name = "U_BA_IsFA")
    private Character uBAIsFA;
    @Column(name = "U_BA_TypID")
    private Integer uBATypID;
    @Column(name = "U_BA_NumID")
    private Integer uBANumID;
    @Column(name = "U_BA_LVAFrom")
    private BigDecimal uBALVAFrom;
    @Column(name = "U_BA_LVA")
    private BigDecimal uBaLva;
    @Size(max = 6)
    @Column(name = "U_U_Mate")
    private String uUMate;
    @Size(max = 30)
    @Column(name = "U_Grupo")
    private String uGrupo;
    @Size(max = 16)
    @Column(name = "U_SubGrupo")
    private String uSubGrupo;
    @Size(max = 100)
    @Column(name = "U_U_NomWeb")
    private String uUNomWeb;
    @Size(max = 200)
    @Column(name = "U_U_PicPro")
    private String uUPicPro;
    @Size(max = 25)
    @Column(name = "U_U_Color")
    private String uUColor;
    @Size(max = 100)
    @Column(name = "U_U_Ref_Pro")
    private String uURefPro;
    @Size(max = 100)
    @Column(name = "U_U_Ref_Aduana")
    private String uURefAduana;
    @Size(max = 1073741823)
    @Column(name = "U_U_Des_Aduana")
    private String uUDesAduana;
    @Size(max = 10)
    @Column(name = "U_U_Cod_Aran")
    private String uUCodAran;
    @Size(max = 10)
    @Column(name = "U_U_Carat")
    private String uUCarat;
    @Size(max = 30)
    @Column(name = "U_U_Num_Part")
    private String uUNumPart;
    @Size(max = 100)
    @Column(name = "U_U_Col_Estru")
    private String uUColEstru;
    @Size(max = 254)
    @Column(name = "U_U_Pal_Cla")
    private String uUPalCla;
    @Size(max = 1073741823)
    @Column(name = "U_U_Alt")
    private String uUAlt;
    @Size(max = 20)
    @Column(name = "U_U_Hab_Des")
    private String uUHabDes;
    @Column(name = "U_U_Act_Qn")
    @Temporal(TemporalType.TIMESTAMP)
    private Date uUActQn;
    @Size(max = 1073741823)
    @Column(name = "U_descripciona")
    private String udescripciona;
    @Size(max = 1073741823)
    @Column(name = "U_Cuidados")
    private String uCuidados;
    @Column(name = "U_Cojineria")
    private Integer uCojineria;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 10)
    @Column(name = "U_Type")
    private String uType;
    @Column(name = "U_NWR_SCRP")
    private BigDecimal uNwrScrp;
    @Column(name = "U_NWRWOPO")
    private Short uNwrwopo;
    @Column(name = "U_NWR_MATRIX")
    private Short uNwrMatrix;
    @Column(name = "U_NWR_AutoShipment")
    private Character uNWRAutoShipment;
    @Size(max = 1073741823)
    @Column(name = "U_materiales")
    private String umateriales;
    @Size(max = 10)
    @Column(name = "U_Numpartes")
    private String uNumpartes;
    @Size(max = 200)
    @Column(name = "U_foto_hd")
    private String ufotohd;
    @Size(max = 100)
    @Column(name = "U_modelo")
    private String umodelo;
    @Basic(optional = false)
    @NotNull
    @Column(name = "U_escanear_bot")
    private Character uescanearbot;
    @Size(max = 30)
    @Column(name = "U_id_youtube")
    private String uidyoutube;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 10)
    @Column(name = "U_fotosOK")
    private String ufotosOK;
    @Size(max = 30)
    @Column(name = "U_talla")
    private String utalla;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 3)
    @Column(name = "U_Posicion_Chaise")
    private String uPosicionChaise;
    @Size(max = 1073741823)
    @Column(name = "U_registro_cambio")
    private String uregistrocambio;
    @Size(max = 190)
    @Column(name = "U_DescCorta")
    private String uDescCorta;
    @Size(max = 10)
    @Column(name = "U_Descontinuado")
    private String uDescontinuado;
    @Size(max = 10)
    @Column(name = "U_Modelado")
    private String uModelado;
    @Column(name = "U_reprocesarfotos")
    @Temporal(TemporalType.TIMESTAMP)
    private Date ureprocesarfotos;
    @Size(max = 15)
    @Column(name = "U_codigoEan")
    private String uCodigoEan;
    @Column(name = "U_FactorRedondeo")
    private String uFactorRedondeo;
    @Column(name = "U_Coleccion")
    private String uColeccion;
    @Column(name = "U_CodigoMarca")
    private String uCodigoMarca;
    @Column(name = "U_Bloqueado")
    private String uBloqueado;
    @Column(name = "U_ID_MERCADOLIBRE")
    private String uIdMercadoLibre;
    @Column(name = "U_DescripcionMdl")
    private String uDescripcionML;
    @Column(name = "U_nombreMCL")
    private String uNombreMCL;

    public ItemInventario() {
    }

    public ItemInventario(String itemCode) {
        this.itemCode = itemCode;
    }

    public ItemInventario(String itemCode, String uType, Character uescanearbot, String ufotosOK, String uPosicionChaise) {
        this.itemCode = itemCode;
        this.uType = uType;
        this.uescanearbot = uescanearbot;
        this.ufotosOK = ufotosOK;
        this.uPosicionChaise = uPosicionChaise;
    }

    public String getItemCode() {
        return itemCode;
    }

    public void setItemCode(String itemCode) {
        this.itemCode = itemCode;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getFrgnName() {
        return frgnName;
    }

    public void setFrgnName(String frgnName) {
        this.frgnName = frgnName;
    }

    public Short getItmsGrpCod() {
        return itmsGrpCod;
    }

    public void setItmsGrpCod(Short itmsGrpCod) {
        this.itmsGrpCod = itmsGrpCod;
    }

    public Short getCstGrpCode() {
        return cstGrpCode;
    }

    public void setCstGrpCode(Short cstGrpCode) {
        this.cstGrpCode = cstGrpCode;
    }

    public String getVatGourpSa() {
        return vatGourpSa;
    }

    public void setVatGourpSa(String vatGourpSa) {
        this.vatGourpSa = vatGourpSa;
    }

    public String getCodeBars() {
        return codeBars;
    }

    public void setCodeBars(String codeBars) {
        this.codeBars = codeBars;
    }

    public Character getVATLiable() {
        return vATLiable;
    }

    public void setVATLiable(Character vATLiable) {
        this.vATLiable = vATLiable;
    }

    public Character getPrchseItem() {
        return prchseItem;
    }

    public void setPrchseItem(Character prchseItem) {
        this.prchseItem = prchseItem;
    }

    public Character getSellItem() {
        return sellItem;
    }

    public void setSellItem(Character sellItem) {
        this.sellItem = sellItem;
    }

    public Character getInvntItem() {
        return invntItem;
    }

    public void setInvntItem(Character invntItem) {
        this.invntItem = invntItem;
    }

    public BigDecimal getOnHand() {
        return onHand;
    }

    public void setOnHand(BigDecimal onHand) {
        this.onHand = onHand;
    }

    public BigDecimal getIsCommited() {
        return isCommited;
    }

    public void setIsCommited(BigDecimal isCommited) {
        this.isCommited = isCommited;
    }

    public BigDecimal getOnOrder() {
        return onOrder;
    }

    public void setOnOrder(BigDecimal onOrder) {
        this.onOrder = onOrder;
    }

    public String getIncomeAcct() {
        return incomeAcct;
    }

    public void setIncomeAcct(String incomeAcct) {
        this.incomeAcct = incomeAcct;
    }

    public String getExmptIncom() {
        return exmptIncom;
    }

    public void setExmptIncom(String exmptIncom) {
        this.exmptIncom = exmptIncom;
    }

    public BigDecimal getMaxLevel() {
        return maxLevel;
    }

    public void setMaxLevel(BigDecimal maxLevel) {
        this.maxLevel = maxLevel;
    }

    public String getDfltWH() {
        return dfltWH;
    }

    public void setDfltWH(String dfltWH) {
        this.dfltWH = dfltWH;
    }

    public String getCardCode() {
        return cardCode;
    }

    public void setCardCode(String cardCode) {
        this.cardCode = cardCode;
    }

    public String getSuppCatNum() {
        return suppCatNum;
    }

    public void setSuppCatNum(String suppCatNum) {
        this.suppCatNum = suppCatNum;
    }

    public String getBuyUnitMsr() {
        return buyUnitMsr;
    }

    public void setBuyUnitMsr(String buyUnitMsr) {
        this.buyUnitMsr = buyUnitMsr;
    }

    public BigDecimal getNumInBuy() {
        return numInBuy;
    }

    public void setNumInBuy(BigDecimal numInBuy) {
        this.numInBuy = numInBuy;
    }

    public BigDecimal getReorderQty() {
        return reorderQty;
    }

    public void setReorderQty(BigDecimal reorderQty) {
        this.reorderQty = reorderQty;
    }

    public BigDecimal getMinLevel() {
        return minLevel;
    }

    public void setMinLevel(BigDecimal minLevel) {
        this.minLevel = minLevel;
    }

    public BigDecimal getLstEvlPric() {
        return lstEvlPric;
    }

    public void setLstEvlPric(BigDecimal lstEvlPric) {
        this.lstEvlPric = lstEvlPric;
    }

    public Date getLstEvlDate() {
        return lstEvlDate;
    }

    public void setLstEvlDate(Date lstEvlDate) {
        this.lstEvlDate = lstEvlDate;
    }

    public BigDecimal getCustomPer() {
        return customPer;
    }

    public void setCustomPer(BigDecimal customPer) {
        this.customPer = customPer;
    }

    public Character getCanceled() {
        return canceled;
    }

    public void setCanceled(Character canceled) {
        this.canceled = canceled;
    }

    public Integer getMnufctTime() {
        return mnufctTime;
    }

    public void setMnufctTime(Integer mnufctTime) {
        this.mnufctTime = mnufctTime;
    }

    public Character getWholSlsTax() {
        return wholSlsTax;
    }

    public void setWholSlsTax(Character wholSlsTax) {
        this.wholSlsTax = wholSlsTax;
    }

    public Character getRetilrTax() {
        return retilrTax;
    }

    public void setRetilrTax(Character retilrTax) {
        this.retilrTax = retilrTax;
    }

    public BigDecimal getSpcialDisc() {
        return spcialDisc;
    }

    public void setSpcialDisc(BigDecimal spcialDisc) {
        this.spcialDisc = spcialDisc;
    }

    public Short getDscountCod() {
        return dscountCod;
    }

    public void setDscountCod(Short dscountCod) {
        this.dscountCod = dscountCod;
    }

    public Character getTrackSales() {
        return trackSales;
    }

    public void setTrackSales(Character trackSales) {
        this.trackSales = trackSales;
    }

    public String getSalUnitMsr() {
        return salUnitMsr;
    }

    public void setSalUnitMsr(String salUnitMsr) {
        this.salUnitMsr = salUnitMsr;
    }

    public BigDecimal getNumInSale() {
        return numInSale;
    }

    public void setNumInSale(BigDecimal numInSale) {
        this.numInSale = numInSale;
    }

    public BigDecimal getConsig() {
        return consig;
    }

    public void setConsig(BigDecimal consig) {
        this.consig = consig;
    }

    public Integer getQueryGroup() {
        return queryGroup;
    }

    public void setQueryGroup(Integer queryGroup) {
        this.queryGroup = queryGroup;
    }

    public BigDecimal getCounted() {
        return counted;
    }

    public void setCounted(BigDecimal counted) {
        this.counted = counted;
    }

    public BigDecimal getOpenBlnc() {
        return openBlnc;
    }

    public void setOpenBlnc(BigDecimal openBlnc) {
        this.openBlnc = openBlnc;
    }

    public Character getEvalSystem() {
        return evalSystem;
    }

    public void setEvalSystem(Character evalSystem) {
        this.evalSystem = evalSystem;
    }

    public Short getUserSign() {
        return userSign;
    }

    public void setUserSign(Short userSign) {
        this.userSign = userSign;
    }

    public Character getFree() {
        return free;
    }

    public void setFree(Character free) {
        this.free = free;
    }

    public String getPicturName() {
        return picturName;
    }

    public void setPicturName(String picturName) {
        this.picturName = picturName;
    }

    public Character getTransfered() {
        return transfered;
    }

    public void setTransfered(Character transfered) {
        this.transfered = transfered;
    }

    public Character getBlncTrnsfr() {
        return blncTrnsfr;
    }

    public void setBlncTrnsfr(Character blncTrnsfr) {
        this.blncTrnsfr = blncTrnsfr;
    }

    public String getUserText() {
        return userText;
    }

    public void setUserText(String userText) {
        this.userText = userText;
    }

    public String getSerialNum() {
        return serialNum;
    }

    public void setSerialNum(String serialNum) {
        this.serialNum = serialNum;
    }

    public BigDecimal getCommisPcnt() {
        return commisPcnt;
    }

    public void setCommisPcnt(BigDecimal commisPcnt) {
        this.commisPcnt = commisPcnt;
    }

    public BigDecimal getCommisSum() {
        return commisSum;
    }

    public void setCommisSum(BigDecimal commisSum) {
        this.commisSum = commisSum;
    }

    public Short getCommisGrp() {
        return commisGrp;
    }

    public void setCommisGrp(Short commisGrp) {
        this.commisGrp = commisGrp;
    }

    public Character getTreeType() {
        return treeType;
    }

    public void setTreeType(Character treeType) {
        this.treeType = treeType;
    }

    public BigDecimal getTreeQty() {
        return treeQty;
    }

    public void setTreeQty(BigDecimal treeQty) {
        this.treeQty = treeQty;
    }

    public BigDecimal getLastPurPrc() {
        return lastPurPrc;
    }

    public void setLastPurPrc(BigDecimal lastPurPrc) {
        this.lastPurPrc = lastPurPrc;
    }

    public String getLastPurCur() {
        return lastPurCur;
    }

    public void setLastPurCur(String lastPurCur) {
        this.lastPurCur = lastPurCur;
    }

    public Date getLastPurDat() {
        return lastPurDat;
    }

    public void setLastPurDat(Date lastPurDat) {
        this.lastPurDat = lastPurDat;
    }

    public String getExitCur() {
        return exitCur;
    }

    public void setExitCur(String exitCur) {
        this.exitCur = exitCur;
    }

    public BigDecimal getExitPrice() {
        return exitPrice;
    }

    public void setExitPrice(BigDecimal exitPrice) {
        this.exitPrice = exitPrice;
    }

    public String getExitWH() {
        return exitWH;
    }

    public void setExitWH(String exitWH) {
        this.exitWH = exitWH;
    }

    public Character getAssetItem() {
        return assetItem;
    }

    public void setAssetItem(Character assetItem) {
        this.assetItem = assetItem;
    }

    public Character getWasCounted() {
        return wasCounted;
    }

    public void setWasCounted(Character wasCounted) {
        this.wasCounted = wasCounted;
    }

    public Character getManSerNum() {
        return manSerNum;
    }

    public void setManSerNum(Character manSerNum) {
        this.manSerNum = manSerNum;
    }

    public BigDecimal getSHeight1() {
        return sHeight1;
    }

    public void setSHeight1(BigDecimal sHeight1) {
        this.sHeight1 = sHeight1;
    }

    public Short getSHght1Unit() {
        return sHght1Unit;
    }

    public void setSHght1Unit(Short sHght1Unit) {
        this.sHght1Unit = sHght1Unit;
    }

    public BigDecimal getSHeight2() {
        return sHeight2;
    }

    public void setSHeight2(BigDecimal sHeight2) {
        this.sHeight2 = sHeight2;
    }

    public Short getSHght2Unit() {
        return sHght2Unit;
    }

    public void setSHght2Unit(Short sHght2Unit) {
        this.sHght2Unit = sHght2Unit;
    }

    public BigDecimal getSWidth1() {
        return sWidth1;
    }

    public void setSWidth1(BigDecimal sWidth1) {
        this.sWidth1 = sWidth1;
    }

    public Short getSWdth1Unit() {
        return sWdth1Unit;
    }

    public void setSWdth1Unit(Short sWdth1Unit) {
        this.sWdth1Unit = sWdth1Unit;
    }

    public BigDecimal getSWidth2() {
        return sWidth2;
    }

    public void setSWidth2(BigDecimal sWidth2) {
        this.sWidth2 = sWidth2;
    }

    public Short getSWdth2Unit() {
        return sWdth2Unit;
    }

    public void setSWdth2Unit(Short sWdth2Unit) {
        this.sWdth2Unit = sWdth2Unit;
    }

    public BigDecimal getSLength1() {
        return sLength1;
    }

    public void setSLength1(BigDecimal sLength1) {
        this.sLength1 = sLength1;
    }

    public Short getSLen1Unit() {
        return sLen1Unit;
    }

    public void setSLen1Unit(Short sLen1Unit) {
        this.sLen1Unit = sLen1Unit;
    }

    public BigDecimal getSlength2() {
        return slength2;
    }

    public void setSlength2(BigDecimal slength2) {
        this.slength2 = slength2;
    }

    public Short getSLen2Unit() {
        return sLen2Unit;
    }

    public void setSLen2Unit(Short sLen2Unit) {
        this.sLen2Unit = sLen2Unit;
    }

    public BigDecimal getSVolume() {
        return sVolume;
    }

    public void setSVolume(BigDecimal sVolume) {
        this.sVolume = sVolume;
    }

    public Short getSVolUnit() {
        return sVolUnit;
    }

    public void setSVolUnit(Short sVolUnit) {
        this.sVolUnit = sVolUnit;
    }

    public BigDecimal getSWeight1() {
        return sWeight1;
    }

    public void setSWeight1(BigDecimal sWeight1) {
        this.sWeight1 = sWeight1;
    }

    public Short getSWght1Unit() {
        return sWght1Unit;
    }

    public void setSWght1Unit(Short sWght1Unit) {
        this.sWght1Unit = sWght1Unit;
    }

    public BigDecimal getSWeight2() {
        return sWeight2;
    }

    public void setSWeight2(BigDecimal sWeight2) {
        this.sWeight2 = sWeight2;
    }

    public Short getSWght2Unit() {
        return sWght2Unit;
    }

    public void setSWght2Unit(Short sWght2Unit) {
        this.sWght2Unit = sWght2Unit;
    }

    public BigDecimal getBHeight1() {
        return bHeight1;
    }

    public void setBHeight1(BigDecimal bHeight1) {
        this.bHeight1 = bHeight1;
    }

    public Short getBHght1Unit() {
        return bHght1Unit;
    }

    public void setBHght1Unit(Short bHght1Unit) {
        this.bHght1Unit = bHght1Unit;
    }

    public BigDecimal getBHeight2() {
        return bHeight2;
    }

    public void setBHeight2(BigDecimal bHeight2) {
        this.bHeight2 = bHeight2;
    }

    public Short getBHght2Unit() {
        return bHght2Unit;
    }

    public void setBHght2Unit(Short bHght2Unit) {
        this.bHght2Unit = bHght2Unit;
    }

    public BigDecimal getBWidth1() {
        return bWidth1;
    }

    public void setBWidth1(BigDecimal bWidth1) {
        this.bWidth1 = bWidth1;
    }

    public Short getBWdth1Unit() {
        return bWdth1Unit;
    }

    public void setBWdth1Unit(Short bWdth1Unit) {
        this.bWdth1Unit = bWdth1Unit;
    }

    public BigDecimal getBWidth2() {
        return bWidth2;
    }

    public void setBWidth2(BigDecimal bWidth2) {
        this.bWidth2 = bWidth2;
    }

    public Short getBWdth2Unit() {
        return bWdth2Unit;
    }

    public void setBWdth2Unit(Short bWdth2Unit) {
        this.bWdth2Unit = bWdth2Unit;
    }

    public BigDecimal getBLength1() {
        return bLength1;
    }

    public void setBLength1(BigDecimal bLength1) {
        this.bLength1 = bLength1;
    }

    public Short getBLen1Unit() {
        return bLen1Unit;
    }

    public void setBLen1Unit(Short bLen1Unit) {
        this.bLen1Unit = bLen1Unit;
    }

    public BigDecimal getBlength2() {
        return blength2;
    }

    public void setBlength2(BigDecimal blength2) {
        this.blength2 = blength2;
    }

    public Short getBLen2Unit() {
        return bLen2Unit;
    }

    public void setBLen2Unit(Short bLen2Unit) {
        this.bLen2Unit = bLen2Unit;
    }

    public BigDecimal getBVolume() {
        return bVolume;
    }

    public void setBVolume(BigDecimal bVolume) {
        this.bVolume = bVolume;
    }

    public Short getBVolUnit() {
        return bVolUnit;
    }

    public void setBVolUnit(Short bVolUnit) {
        this.bVolUnit = bVolUnit;
    }

    public BigDecimal getBWeight1() {
        return bWeight1;
    }

    public void setBWeight1(BigDecimal bWeight1) {
        this.bWeight1 = bWeight1;
    }

    public Short getBWght1Unit() {
        return bWght1Unit;
    }

    public void setBWght1Unit(Short bWght1Unit) {
        this.bWght1Unit = bWght1Unit;
    }

    public BigDecimal getBWeight2() {
        return bWeight2;
    }

    public void setBWeight2(BigDecimal bWeight2) {
        this.bWeight2 = bWeight2;
    }

    public Short getBWght2Unit() {
        return bWght2Unit;
    }

    public void setBWght2Unit(Short bWght2Unit) {
        this.bWght2Unit = bWght2Unit;
    }

    public String getFixCurrCms() {
        return fixCurrCms;
    }

    public void setFixCurrCms(String fixCurrCms) {
        this.fixCurrCms = fixCurrCms;
    }

    public Short getFirmCode() {
        return firmCode;
    }

    public void setFirmCode(Short firmCode) {
        this.firmCode = firmCode;
    }

    public Date getLstSalDate() {
        return lstSalDate;
    }

    public void setLstSalDate(Date lstSalDate) {
        this.lstSalDate = lstSalDate;
    }

    public Character getQryGroup1() {
        return qryGroup1;
    }

    public void setQryGroup1(Character qryGroup1) {
        this.qryGroup1 = qryGroup1;
    }

    public Character getQryGroup2() {
        return qryGroup2;
    }

    public void setQryGroup2(Character qryGroup2) {
        this.qryGroup2 = qryGroup2;
    }

    public Character getQryGroup3() {
        return qryGroup3;
    }

    public void setQryGroup3(Character qryGroup3) {
        this.qryGroup3 = qryGroup3;
    }

    public Character getQryGroup4() {
        return qryGroup4;
    }

    public void setQryGroup4(Character qryGroup4) {
        this.qryGroup4 = qryGroup4;
    }

    public Character getQryGroup5() {
        return qryGroup5;
    }

    public void setQryGroup5(Character qryGroup5) {
        this.qryGroup5 = qryGroup5;
    }

    public Character getQryGroup6() {
        return qryGroup6;
    }

    public void setQryGroup6(Character qryGroup6) {
        this.qryGroup6 = qryGroup6;
    }

    public Character getQryGroup7() {
        return qryGroup7;
    }

    public void setQryGroup7(Character qryGroup7) {
        this.qryGroup7 = qryGroup7;
    }

    public Character getQryGroup8() {
        return qryGroup8;
    }

    public void setQryGroup8(Character qryGroup8) {
        this.qryGroup8 = qryGroup8;
    }

    public Character getQryGroup9() {
        return qryGroup9;
    }

    public void setQryGroup9(Character qryGroup9) {
        this.qryGroup9 = qryGroup9;
    }

    public Character getQryGroup10() {
        return qryGroup10;
    }

    public void setQryGroup10(Character qryGroup10) {
        this.qryGroup10 = qryGroup10;
    }

    public Character getQryGroup11() {
        return qryGroup11;
    }

    public void setQryGroup11(Character qryGroup11) {
        this.qryGroup11 = qryGroup11;
    }

    public Character getQryGroup12() {
        return qryGroup12;
    }

    public void setQryGroup12(Character qryGroup12) {
        this.qryGroup12 = qryGroup12;
    }

    public Character getQryGroup13() {
        return qryGroup13;
    }

    public void setQryGroup13(Character qryGroup13) {
        this.qryGroup13 = qryGroup13;
    }

    public Character getQryGroup14() {
        return qryGroup14;
    }

    public void setQryGroup14(Character qryGroup14) {
        this.qryGroup14 = qryGroup14;
    }

    public Character getQryGroup15() {
        return qryGroup15;
    }

    public void setQryGroup15(Character qryGroup15) {
        this.qryGroup15 = qryGroup15;
    }

    public Character getQryGroup16() {
        return qryGroup16;
    }

    public void setQryGroup16(Character qryGroup16) {
        this.qryGroup16 = qryGroup16;
    }

    public Character getQryGroup17() {
        return qryGroup17;
    }

    public void setQryGroup17(Character qryGroup17) {
        this.qryGroup17 = qryGroup17;
    }

    public Character getQryGroup18() {
        return qryGroup18;
    }

    public void setQryGroup18(Character qryGroup18) {
        this.qryGroup18 = qryGroup18;
    }

    public Character getQryGroup19() {
        return qryGroup19;
    }

    public void setQryGroup19(Character qryGroup19) {
        this.qryGroup19 = qryGroup19;
    }

    public Character getQryGroup20() {
        return qryGroup20;
    }

    public void setQryGroup20(Character qryGroup20) {
        this.qryGroup20 = qryGroup20;
    }

    public Character getQryGroup21() {
        return qryGroup21;
    }

    public void setQryGroup21(Character qryGroup21) {
        this.qryGroup21 = qryGroup21;
    }

    public Character getQryGroup22() {
        return qryGroup22;
    }

    public void setQryGroup22(Character qryGroup22) {
        this.qryGroup22 = qryGroup22;
    }

    public Character getQryGroup23() {
        return qryGroup23;
    }

    public void setQryGroup23(Character qryGroup23) {
        this.qryGroup23 = qryGroup23;
    }

    public Character getQryGroup24() {
        return qryGroup24;
    }

    public void setQryGroup24(Character qryGroup24) {
        this.qryGroup24 = qryGroup24;
    }

    public Character getQryGroup25() {
        return qryGroup25;
    }

    public void setQryGroup25(Character qryGroup25) {
        this.qryGroup25 = qryGroup25;
    }

    public Character getQryGroup26() {
        return qryGroup26;
    }

    public void setQryGroup26(Character qryGroup26) {
        this.qryGroup26 = qryGroup26;
    }

    public Character getQryGroup27() {
        return qryGroup27;
    }

    public void setQryGroup27(Character qryGroup27) {
        this.qryGroup27 = qryGroup27;
    }

    public Character getQryGroup28() {
        return qryGroup28;
    }

    public void setQryGroup28(Character qryGroup28) {
        this.qryGroup28 = qryGroup28;
    }

    public Character getQryGroup29() {
        return qryGroup29;
    }

    public void setQryGroup29(Character qryGroup29) {
        this.qryGroup29 = qryGroup29;
    }

    public Character getQryGroup30() {
        return qryGroup30;
    }

    public void setQryGroup30(Character qryGroup30) {
        this.qryGroup30 = qryGroup30;
    }

    public Character getQryGroup31() {
        return qryGroup31;
    }

    public void setQryGroup31(Character qryGroup31) {
        this.qryGroup31 = qryGroup31;
    }

    public Character getQryGroup32() {
        return qryGroup32;
    }

    public void setQryGroup32(Character qryGroup32) {
        this.qryGroup32 = qryGroup32;
    }

    public Character getQryGroup33() {
        return qryGroup33;
    }

    public void setQryGroup33(Character qryGroup33) {
        this.qryGroup33 = qryGroup33;
    }

    public Character getQryGroup34() {
        return qryGroup34;
    }

    public void setQryGroup34(Character qryGroup34) {
        this.qryGroup34 = qryGroup34;
    }

    public Character getQryGroup35() {
        return qryGroup35;
    }

    public void setQryGroup35(Character qryGroup35) {
        this.qryGroup35 = qryGroup35;
    }

    public Character getQryGroup36() {
        return qryGroup36;
    }

    public void setQryGroup36(Character qryGroup36) {
        this.qryGroup36 = qryGroup36;
    }

    public Character getQryGroup37() {
        return qryGroup37;
    }

    public void setQryGroup37(Character qryGroup37) {
        this.qryGroup37 = qryGroup37;
    }

    public Character getQryGroup38() {
        return qryGroup38;
    }

    public void setQryGroup38(Character qryGroup38) {
        this.qryGroup38 = qryGroup38;
    }

    public Character getQryGroup39() {
        return qryGroup39;
    }

    public void setQryGroup39(Character qryGroup39) {
        this.qryGroup39 = qryGroup39;
    }

    public Character getQryGroup40() {
        return qryGroup40;
    }

    public void setQryGroup40(Character qryGroup40) {
        this.qryGroup40 = qryGroup40;
    }

    public Character getQryGroup41() {
        return qryGroup41;
    }

    public void setQryGroup41(Character qryGroup41) {
        this.qryGroup41 = qryGroup41;
    }

    public Character getQryGroup42() {
        return qryGroup42;
    }

    public void setQryGroup42(Character qryGroup42) {
        this.qryGroup42 = qryGroup42;
    }

    public Character getQryGroup43() {
        return qryGroup43;
    }

    public void setQryGroup43(Character qryGroup43) {
        this.qryGroup43 = qryGroup43;
    }

    public Character getQryGroup44() {
        return qryGroup44;
    }

    public void setQryGroup44(Character qryGroup44) {
        this.qryGroup44 = qryGroup44;
    }

    public Character getQryGroup45() {
        return qryGroup45;
    }

    public void setQryGroup45(Character qryGroup45) {
        this.qryGroup45 = qryGroup45;
    }

    public Character getQryGroup46() {
        return qryGroup46;
    }

    public void setQryGroup46(Character qryGroup46) {
        this.qryGroup46 = qryGroup46;
    }

    public Character getQryGroup47() {
        return qryGroup47;
    }

    public void setQryGroup47(Character qryGroup47) {
        this.qryGroup47 = qryGroup47;
    }

    public Character getQryGroup48() {
        return qryGroup48;
    }

    public void setQryGroup48(Character qryGroup48) {
        this.qryGroup48 = qryGroup48;
    }

    public Character getQryGroup49() {
        return qryGroup49;
    }

    public void setQryGroup49(Character qryGroup49) {
        this.qryGroup49 = qryGroup49;
    }

    public Character getQryGroup50() {
        return qryGroup50;
    }

    public void setQryGroup50(Character qryGroup50) {
        this.qryGroup50 = qryGroup50;
    }

    public Character getQryGroup51() {
        return qryGroup51;
    }

    public void setQryGroup51(Character qryGroup51) {
        this.qryGroup51 = qryGroup51;
    }

    public Character getQryGroup52() {
        return qryGroup52;
    }

    public void setQryGroup52(Character qryGroup52) {
        this.qryGroup52 = qryGroup52;
    }

    public Character getQryGroup53() {
        return qryGroup53;
    }

    public void setQryGroup53(Character qryGroup53) {
        this.qryGroup53 = qryGroup53;
    }

    public Character getQryGroup54() {
        return qryGroup54;
    }

    public void setQryGroup54(Character qryGroup54) {
        this.qryGroup54 = qryGroup54;
    }

    public Character getQryGroup55() {
        return qryGroup55;
    }

    public void setQryGroup55(Character qryGroup55) {
        this.qryGroup55 = qryGroup55;
    }

    public Character getQryGroup56() {
        return qryGroup56;
    }

    public void setQryGroup56(Character qryGroup56) {
        this.qryGroup56 = qryGroup56;
    }

    public Character getQryGroup57() {
        return qryGroup57;
    }

    public void setQryGroup57(Character qryGroup57) {
        this.qryGroup57 = qryGroup57;
    }

    public Character getQryGroup58() {
        return qryGroup58;
    }

    public void setQryGroup58(Character qryGroup58) {
        this.qryGroup58 = qryGroup58;
    }

    public Character getQryGroup59() {
        return qryGroup59;
    }

    public void setQryGroup59(Character qryGroup59) {
        this.qryGroup59 = qryGroup59;
    }

    public Character getQryGroup60() {
        return qryGroup60;
    }

    public void setQryGroup60(Character qryGroup60) {
        this.qryGroup60 = qryGroup60;
    }

    public Character getQryGroup61() {
        return qryGroup61;
    }

    public void setQryGroup61(Character qryGroup61) {
        this.qryGroup61 = qryGroup61;
    }

    public Character getQryGroup62() {
        return qryGroup62;
    }

    public void setQryGroup62(Character qryGroup62) {
        this.qryGroup62 = qryGroup62;
    }

    public Character getQryGroup63() {
        return qryGroup63;
    }

    public void setQryGroup63(Character qryGroup63) {
        this.qryGroup63 = qryGroup63;
    }

    public Character getQryGroup64() {
        return qryGroup64;
    }

    public void setQryGroup64(Character qryGroup64) {
        this.qryGroup64 = qryGroup64;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    public String getExportCode() {
        return exportCode;
    }

    public void setExportCode(String exportCode) {
        this.exportCode = exportCode;
    }

    public BigDecimal getSalFactor1() {
        return salFactor1;
    }

    public void setSalFactor1(BigDecimal salFactor1) {
        this.salFactor1 = salFactor1;
    }

    public BigDecimal getSalFactor2() {
        return salFactor2;
    }

    public void setSalFactor2(BigDecimal salFactor2) {
        this.salFactor2 = salFactor2;
    }

    public BigDecimal getSalFactor3() {
        return salFactor3;
    }

    public void setSalFactor3(BigDecimal salFactor3) {
        this.salFactor3 = salFactor3;
    }

    public BigDecimal getSalFactor4() {
        return salFactor4;
    }

    public void setSalFactor4(BigDecimal salFactor4) {
        this.salFactor4 = salFactor4;
    }

    public BigDecimal getPurFactor1() {
        return purFactor1;
    }

    public void setPurFactor1(BigDecimal purFactor1) {
        this.purFactor1 = purFactor1;
    }

    public BigDecimal getPurFactor2() {
        return purFactor2;
    }

    public void setPurFactor2(BigDecimal purFactor2) {
        this.purFactor2 = purFactor2;
    }

    public BigDecimal getPurFactor3() {
        return purFactor3;
    }

    public void setPurFactor3(BigDecimal purFactor3) {
        this.purFactor3 = purFactor3;
    }

    public BigDecimal getPurFactor4() {
        return purFactor4;
    }

    public void setPurFactor4(BigDecimal purFactor4) {
        this.purFactor4 = purFactor4;
    }

    public String getSalFormula() {
        return salFormula;
    }

    public void setSalFormula(String salFormula) {
        this.salFormula = salFormula;
    }

    public String getPurFormula() {
        return purFormula;
    }

    public void setPurFormula(String purFormula) {
        this.purFormula = purFormula;
    }

    public String getVatGroupPu() {
        return vatGroupPu;
    }

    public void setVatGroupPu(String vatGroupPu) {
        this.vatGroupPu = vatGroupPu;
    }

    public BigDecimal getAvgPrice() {
        return avgPrice;
    }

    public void setAvgPrice(BigDecimal avgPrice) {
        this.avgPrice = avgPrice;
    }

    public String getPurPackMsr() {
        return purPackMsr;
    }

    public void setPurPackMsr(String purPackMsr) {
        this.purPackMsr = purPackMsr;
    }

    public BigDecimal getPurPackUn() {
        return purPackUn;
    }

    public void setPurPackUn(BigDecimal purPackUn) {
        this.purPackUn = purPackUn;
    }

    public String getSalPackMsr() {
        return salPackMsr;
    }

    public void setSalPackMsr(String salPackMsr) {
        this.salPackMsr = salPackMsr;
    }

    public BigDecimal getSalPackUn() {
        return salPackUn;
    }

    public void setSalPackUn(BigDecimal salPackUn) {
        this.salPackUn = salPackUn;
    }

    public Short getSCNCounter() {
        return sCNCounter;
    }

    public void setSCNCounter(Short sCNCounter) {
        this.sCNCounter = sCNCounter;
    }

    public Character getManBtchNum() {
        return manBtchNum;
    }

    public void setManBtchNum(Character manBtchNum) {
        this.manBtchNum = manBtchNum;
    }

    public Character getManOutOnly() {
        return manOutOnly;
    }

    public void setManOutOnly(Character manOutOnly) {
        this.manOutOnly = manOutOnly;
    }

    public Character getDataSource() {
        return dataSource;
    }

    public void setDataSource(Character dataSource) {
        this.dataSource = dataSource;
    }

    public Character getValidFor() {
        return validFor;
    }

    public void setValidFor(Character validFor) {
        this.validFor = validFor;
    }

    public Date getValidFrom() {
        return validFrom;
    }

    public void setValidFrom(Date validFrom) {
        this.validFrom = validFrom;
    }

    public Date getValidTo() {
        return validTo;
    }

    public void setValidTo(Date validTo) {
        this.validTo = validTo;
    }

    public Character getFrozenFor() {
        return frozenFor;
    }

    public void setFrozenFor(Character frozenFor) {
        this.frozenFor = frozenFor;
    }

    public Date getFrozenFrom() {
        return frozenFrom;
    }

    public void setFrozenFrom(Date frozenFrom) {
        this.frozenFrom = frozenFrom;
    }

    public Date getFrozenTo() {
        return frozenTo;
    }

    public void setFrozenTo(Date frozenTo) {
        this.frozenTo = frozenTo;
    }

    public Character getBlockOut() {
        return blockOut;
    }

    public void setBlockOut(Character blockOut) {
        this.blockOut = blockOut;
    }

    public String getValidComm() {
        return validComm;
    }

    public void setValidComm(String validComm) {
        this.validComm = validComm;
    }

    public String getFrozenComm() {
        return frozenComm;
    }

    public void setFrozenComm(String frozenComm) {
        this.frozenComm = frozenComm;
    }

    public Integer getLogInstanc() {
        return logInstanc;
    }

    public void setLogInstanc(Integer logInstanc) {
        this.logInstanc = logInstanc;
    }

    public String getObjType() {
        return objType;
    }

    public void setObjType(String objType) {
        this.objType = objType;
    }

    public String getSww() {
        return sww;
    }

    public void setSww(String sww) {
        this.sww = sww;
    }

    public Character getDeleted() {
        return deleted;
    }

    public void setDeleted(Character deleted) {
        this.deleted = deleted;
    }

    public Integer getDocEntry() {
        return docEntry;
    }

    public void setDocEntry(Integer docEntry) {
        this.docEntry = docEntry;
    }

    public String getExpensAcct() {
        return expensAcct;
    }

    public void setExpensAcct(String expensAcct) {
        this.expensAcct = expensAcct;
    }

    public String getFrgnInAcct() {
        return frgnInAcct;
    }

    public void setFrgnInAcct(String frgnInAcct) {
        this.frgnInAcct = frgnInAcct;
    }

    public Short getShipType() {
        return shipType;
    }

    public void setShipType(Short shipType) {
        this.shipType = shipType;
    }

    public Character getGLMethod() {
        return gLMethod;
    }

    public void setGLMethod(Character gLMethod) {
        this.gLMethod = gLMethod;
    }

    public String getECInAcct() {
        return eCInAcct;
    }

    public void setECInAcct(String eCInAcct) {
        this.eCInAcct = eCInAcct;
    }

    public String getFrgnExpAcc() {
        return frgnExpAcc;
    }

    public void setFrgnExpAcc(String frgnExpAcc) {
        this.frgnExpAcc = frgnExpAcc;
    }

    public String getECExpAcc() {
        return eCExpAcc;
    }

    public void setECExpAcc(String eCExpAcc) {
        this.eCExpAcc = eCExpAcc;
    }

    public Character getTaxType() {
        return taxType;
    }

    public void setTaxType(Character taxType) {
        this.taxType = taxType;
    }

    public Character getByWh() {
        return byWh;
    }

    public void setByWh(Character byWh) {
        this.byWh = byWh;
    }

    public Character getWTLiable() {
        return wTLiable;
    }

    public void setWTLiable(Character wTLiable) {
        this.wTLiable = wTLiable;
    }

    public Character getItemType() {
        return itemType;
    }

    public void setItemType(Character itemType) {
        this.itemType = itemType;
    }

    public String getWarrntTmpl() {
        return warrntTmpl;
    }

    public void setWarrntTmpl(String warrntTmpl) {
        this.warrntTmpl = warrntTmpl;
    }

    public String getBaseUnit() {
        return baseUnit;
    }

    public void setBaseUnit(String baseUnit) {
        this.baseUnit = baseUnit;
    }

    public String getCountryOrg() {
        return countryOrg;
    }

    public void setCountryOrg(String countryOrg) {
        this.countryOrg = countryOrg;
    }

    public BigDecimal getStockValue() {
        return stockValue;
    }

    public void setStockValue(BigDecimal stockValue) {
        this.stockValue = stockValue;
    }

    public Character getPhantom() {
        return phantom;
    }

    public void setPhantom(Character phantom) {
        this.phantom = phantom;
    }

    public Character getIssueMthd() {
        return issueMthd;
    }

    public void setIssueMthd(Character issueMthd) {
        this.issueMthd = issueMthd;
    }

    public Character getFree1() {
        return free1;
    }

    public void setFree1(Character free1) {
        this.free1 = free1;
    }

    public BigDecimal getPricingPrc() {
        return pricingPrc;
    }

    public void setPricingPrc(BigDecimal pricingPrc) {
        this.pricingPrc = pricingPrc;
    }

    public Character getMngMethod() {
        return mngMethod;
    }

    public void setMngMethod(Character mngMethod) {
        this.mngMethod = mngMethod;
    }

    public BigDecimal getReorderPnt() {
        return reorderPnt;
    }

    public void setReorderPnt(BigDecimal reorderPnt) {
        this.reorderPnt = reorderPnt;
    }

    public String getInvntryUom() {
        return invntryUom;
    }

    public void setInvntryUom(String invntryUom) {
        this.invntryUom = invntryUom;
    }

    public Character getPlaningSys() {
        return planingSys;
    }

    public void setPlaningSys(Character planingSys) {
        this.planingSys = planingSys;
    }

    public Character getPrcrmntMtd() {
        return prcrmntMtd;
    }

    public void setPrcrmntMtd(Character prcrmntMtd) {
        this.prcrmntMtd = prcrmntMtd;
    }

    public Short getOrdrIntrvl() {
        return ordrIntrvl;
    }

    public void setOrdrIntrvl(Short ordrIntrvl) {
        this.ordrIntrvl = ordrIntrvl;
    }

    public BigDecimal getOrdrMulti() {
        return ordrMulti;
    }

    public void setOrdrMulti(BigDecimal ordrMulti) {
        this.ordrMulti = ordrMulti;
    }

    public BigDecimal getMinOrdrQty() {
        return minOrdrQty;
    }

    public void setMinOrdrQty(BigDecimal minOrdrQty) {
        this.minOrdrQty = minOrdrQty;
    }

    public Integer getLeadTime() {
        return leadTime;
    }

    public void setLeadTime(Integer leadTime) {
        this.leadTime = leadTime;
    }

    public Character getIndirctTax() {
        return indirctTax;
    }

    public void setIndirctTax(Character indirctTax) {
        this.indirctTax = indirctTax;
    }

    public String getTaxCodeAR() {
        return taxCodeAR;
    }

    public void setTaxCodeAR(String taxCodeAR) {
        this.taxCodeAR = taxCodeAR;
    }

    public String getTaxCodeAP() {
        return taxCodeAP;
    }

    public void setTaxCodeAP(String taxCodeAP) {
        this.taxCodeAP = taxCodeAP;
    }

    public Integer getOSvcCode() {
        return oSvcCode;
    }

    public void setOSvcCode(Integer oSvcCode) {
        this.oSvcCode = oSvcCode;
    }

    public Integer getISvcCode() {
        return iSvcCode;
    }

    public void setISvcCode(Integer iSvcCode) {
        this.iSvcCode = iSvcCode;
    }

    public Integer getServiceGrp() {
        return serviceGrp;
    }

    public void setServiceGrp(Integer serviceGrp) {
        this.serviceGrp = serviceGrp;
    }

    public Integer getNCMCode() {
        return nCMCode;
    }

    public void setNCMCode(Integer nCMCode) {
        this.nCMCode = nCMCode;
    }

    public String getMatType() {
        return matType;
    }

    public void setMatType(String matType) {
        this.matType = matType;
    }

    public Integer getMatGrp() {
        return matGrp;
    }

    public void setMatGrp(Integer matGrp) {
        this.matGrp = matGrp;
    }

    public String getProductSrc() {
        return productSrc;
    }

    public void setProductSrc(String productSrc) {
        this.productSrc = productSrc;
    }

    public Integer getServiceCtg() {
        return serviceCtg;
    }

    public void setServiceCtg(Integer serviceCtg) {
        this.serviceCtg = serviceCtg;
    }

    public Character getItemClass() {
        return itemClass;
    }

    public void setItemClass(Character itemClass) {
        this.itemClass = itemClass;
    }

    public Character getExcisable() {
        return excisable;
    }

    public void setExcisable(Character excisable) {
        this.excisable = excisable;
    }

    public Integer getChapterID() {
        return chapterID;
    }

    public void setChapterID(Integer chapterID) {
        this.chapterID = chapterID;
    }

    public String getNotifyASN() {
        return notifyASN;
    }

    public void setNotifyASN(String notifyASN) {
        this.notifyASN = notifyASN;
    }

    public String getProAssNum() {
        return proAssNum;
    }

    public void setProAssNum(String proAssNum) {
        this.proAssNum = proAssNum;
    }

    public BigDecimal getAssblValue() {
        return assblValue;
    }

    public void setAssblValue(BigDecimal assblValue) {
        this.assblValue = assblValue;
    }

    public Integer getDNFEntry() {
        return dNFEntry;
    }

    public void setDNFEntry(Integer dNFEntry) {
        this.dNFEntry = dNFEntry;
    }

    public Short getUserSign2() {
        return userSign2;
    }

    public void setUserSign2(Short userSign2) {
        this.userSign2 = userSign2;
    }

    public String getSpec() {
        return spec;
    }

    public void setSpec(String spec) {
        this.spec = spec;
    }

    public String getTaxCtg() {
        return taxCtg;
    }

    public void setTaxCtg(String taxCtg) {
        this.taxCtg = taxCtg;
    }

    public Short getSeries() {
        return series;
    }

    public void setSeries(Short series) {
        this.series = series;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public Integer getFuelCode() {
        return fuelCode;
    }

    public void setFuelCode(Integer fuelCode) {
        this.fuelCode = fuelCode;
    }

    public String getBeverTblC() {
        return beverTblC;
    }

    public void setBeverTblC(String beverTblC) {
        this.beverTblC = beverTblC;
    }

    public String getBeverGrpC() {
        return beverGrpC;
    }

    public void setBeverGrpC(String beverGrpC) {
        this.beverGrpC = beverGrpC;
    }

    public Integer getBeverTM() {
        return beverTM;
    }

    public void setBeverTM(Integer beverTM) {
        this.beverTM = beverTM;
    }

    public String getAttachment() {
        return attachment;
    }

    public void setAttachment(String attachment) {
        this.attachment = attachment;
    }

    public Integer getAtcEntry() {
        return atcEntry;
    }

    public void setAtcEntry(Integer atcEntry) {
        this.atcEntry = atcEntry;
    }

    public Integer getToleranDay() {
        return toleranDay;
    }

    public void setToleranDay(Integer toleranDay) {
        this.toleranDay = toleranDay;
    }

    public Integer getUgpEntry() {
        return ugpEntry;
    }

    public void setUgpEntry(Integer ugpEntry) {
        this.ugpEntry = ugpEntry;
    }

    public Integer getPUoMEntry() {
        return pUoMEntry;
    }

    public void setPUoMEntry(Integer pUoMEntry) {
        this.pUoMEntry = pUoMEntry;
    }

    public Integer getSUoMEntry() {
        return sUoMEntry;
    }

    public void setSUoMEntry(Integer sUoMEntry) {
        this.sUoMEntry = sUoMEntry;
    }

    public Integer getIUoMEntry() {
        return iUoMEntry;
    }

    public void setIUoMEntry(Integer iUoMEntry) {
        this.iUoMEntry = iUoMEntry;
    }

    public Short getIssuePriBy() {
        return issuePriBy;
    }

    public void setIssuePriBy(Short issuePriBy) {
        this.issuePriBy = issuePriBy;
    }

    public String getAssetClass() {
        return assetClass;
    }

    public void setAssetClass(String assetClass) {
        this.assetClass = assetClass;
    }

    public String getAssetGroup() {
        return assetGroup;
    }

    public void setAssetGroup(String assetGroup) {
        this.assetGroup = assetGroup;
    }

    public String getInventryNo() {
        return inventryNo;
    }

    public void setInventryNo(String inventryNo) {
        this.inventryNo = inventryNo;
    }

    public Integer getTechnician() {
        return technician;
    }

    public void setTechnician(Integer technician) {
        this.technician = technician;
    }

    public Integer getEmployee() {
        return employee;
    }

    public void setEmployee(Integer employee) {
        this.employee = employee;
    }

    public Integer getLocation() {
        return location;
    }

    public void setLocation(Integer location) {
        this.location = location;
    }

    public Character getStatAsset() {
        return statAsset;
    }

    public void setStatAsset(Character statAsset) {
        this.statAsset = statAsset;
    }

    public Character getCession() {
        return cession;
    }

    public void setCession(Character cession) {
        this.cession = cession;
    }

    public Character getDeacAftUL() {
        return deacAftUL;
    }

    public void setDeacAftUL(Character deacAftUL) {
        this.deacAftUL = deacAftUL;
    }

    public Character getAsstStatus() {
        return asstStatus;
    }

    public void setAsstStatus(Character asstStatus) {
        this.asstStatus = asstStatus;
    }

    public Date getCapDate() {
        return capDate;
    }

    public void setCapDate(Date capDate) {
        this.capDate = capDate;
    }

    public Date getAcqDate() {
        return acqDate;
    }

    public void setAcqDate(Date acqDate) {
        this.acqDate = acqDate;
    }

    public Date getRetDate() {
        return retDate;
    }

    public void setRetDate(Date retDate) {
        this.retDate = retDate;
    }

    public Character getGLPickMeth() {
        return gLPickMeth;
    }

    public void setGLPickMeth(Character gLPickMeth) {
        this.gLPickMeth = gLPickMeth;
    }

    public Character getNoDiscount() {
        return noDiscount;
    }

    public void setNoDiscount(Character noDiscount) {
        this.noDiscount = noDiscount;
    }

    public Character getMgrByQty() {
        return mgrByQty;
    }

    public void setMgrByQty(Character mgrByQty) {
        this.mgrByQty = mgrByQty;
    }

    public String getAssetRmk1() {
        return assetRmk1;
    }

    public void setAssetRmk1(String assetRmk1) {
        this.assetRmk1 = assetRmk1;
    }

    public String getAssetRmk2() {
        return assetRmk2;
    }

    public void setAssetRmk2(String assetRmk2) {
        this.assetRmk2 = assetRmk2;
    }

    public BigDecimal getAssetAmnt1() {
        return assetAmnt1;
    }

    public void setAssetAmnt1(BigDecimal assetAmnt1) {
        this.assetAmnt1 = assetAmnt1;
    }

    public BigDecimal getAssetAmnt2() {
        return assetAmnt2;
    }

    public void setAssetAmnt2(BigDecimal assetAmnt2) {
        this.assetAmnt2 = assetAmnt2;
    }

    public String getDeprGroup() {
        return deprGroup;
    }

    public void setDeprGroup(String deprGroup) {
        this.deprGroup = deprGroup;
    }

    public String getAssetSerNo() {
        return assetSerNo;
    }

    public void setAssetSerNo(String assetSerNo) {
        this.assetSerNo = assetSerNo;
    }

    public String getCntUnitMsr() {
        return cntUnitMsr;
    }

    public void setCntUnitMsr(String cntUnitMsr) {
        this.cntUnitMsr = cntUnitMsr;
    }

    public BigDecimal getNumInCnt() {
        return numInCnt;
    }

    public void setNumInCnt(BigDecimal numInCnt) {
        this.numInCnt = numInCnt;
    }

    public Integer getINUoMEntry() {
        return iNUoMEntry;
    }

    public void setINUoMEntry(Integer iNUoMEntry) {
        this.iNUoMEntry = iNUoMEntry;
    }

    public Character getOneBOneRec() {
        return oneBOneRec;
    }

    public void setOneBOneRec(Character oneBOneRec) {
        this.oneBOneRec = oneBOneRec;
    }

    public String getRuleCode() {
        return ruleCode;
    }

    public void setRuleCode(String ruleCode) {
        this.ruleCode = ruleCode;
    }

    public String getScsCode() {
        return scsCode;
    }

    public void setScsCode(String scsCode) {
        this.scsCode = scsCode;
    }

    public String getSpProdType() {
        return spProdType;
    }

    public void setSpProdType(String spProdType) {
        this.spProdType = spProdType;
    }

    public BigDecimal getIWeight1() {
        return iWeight1;
    }

    public void setIWeight1(BigDecimal iWeight1) {
        this.iWeight1 = iWeight1;
    }

    public Short getIWght1Unit() {
        return iWght1Unit;
    }

    public void setIWght1Unit(Short iWght1Unit) {
        this.iWght1Unit = iWght1Unit;
    }

    public BigDecimal getIWeight2() {
        return iWeight2;
    }

    public void setIWeight2(BigDecimal iWeight2) {
        this.iWeight2 = iWeight2;
    }

    public Short getIWght2Unit() {
        return iWght2Unit;
    }

    public void setIWght2Unit(Short iWght2Unit) {
        this.iWght2Unit = iWght2Unit;
    }

    public Character getCompoWH() {
        return compoWH;
    }

    public void setCompoWH(Character compoWH) {
        this.compoWH = compoWH;
    }

    public Character getUBAIsFA() {
        return uBAIsFA;
    }

    public void setUBAIsFA(Character uBAIsFA) {
        this.uBAIsFA = uBAIsFA;
    }

    public Integer getUBATypID() {
        return uBATypID;
    }

    public void setUBATypID(Integer uBATypID) {
        this.uBATypID = uBATypID;
    }

    public Integer getUBANumID() {
        return uBANumID;
    }

    public void setUBANumID(Integer uBANumID) {
        this.uBANumID = uBANumID;
    }

    public BigDecimal getUBALVAFrom() {
        return uBALVAFrom;
    }

    public void setUBALVAFrom(BigDecimal uBALVAFrom) {
        this.uBALVAFrom = uBALVAFrom;
    }

    public BigDecimal getUBaLva() {
        return uBaLva;
    }

    public void setUBaLva(BigDecimal uBaLva) {
        this.uBaLva = uBaLva;
    }

    public String getUUMate() {
        return uUMate;
    }

    public void setUUMate(String uUMate) {
        this.uUMate = uUMate;
    }

    public String getUGrupo() {
        return uGrupo;
    }

    public void setUGrupo(String uGrupo) {
        this.uGrupo = uGrupo;
    }

    public String getUSubGrupo() {
        return uSubGrupo;
    }

    public void setUSubGrupo(String uSubGrupo) {
        this.uSubGrupo = uSubGrupo;
    }

    public String getUUNomWeb() {
        return uUNomWeb;
    }

    public void setUUNomWeb(String uUNomWeb) {
        this.uUNomWeb = uUNomWeb;
    }

    public String getUUPicPro() {
        return uUPicPro;
    }

    public void setUUPicPro(String uUPicPro) {
        this.uUPicPro = uUPicPro;
    }

    public String getUUColor() {
        return uUColor;
    }

    public void setUUColor(String uUColor) {
        this.uUColor = uUColor;
    }

    public String getUURefPro() {
        return uURefPro;
    }

    public void setUURefPro(String uURefPro) {
        this.uURefPro = uURefPro;
    }

    public String getUURefAduana() {
        return uURefAduana;
    }

    public void setUURefAduana(String uURefAduana) {
        this.uURefAduana = uURefAduana;
    }

    public String getUUDesAduana() {
        return uUDesAduana;
    }

    public void setUUDesAduana(String uUDesAduana) {
        this.uUDesAduana = uUDesAduana;
    }

    public String getUUCodAran() {
        return uUCodAran;
    }

    public void setUUCodAran(String uUCodAran) {
        this.uUCodAran = uUCodAran;
    }

    public String getUUCarat() {
        return uUCarat;
    }

    public void setUUCarat(String uUCarat) {
        this.uUCarat = uUCarat;
    }

    public String getUUNumPart() {
        return uUNumPart;
    }

    public void setUUNumPart(String uUNumPart) {
        this.uUNumPart = uUNumPart;
    }

    public String getUUColEstru() {
        return uUColEstru;
    }

    public void setUUColEstru(String uUColEstru) {
        this.uUColEstru = uUColEstru;
    }

    public String getUUPalCla() {
        return uUPalCla;
    }

    public void setUUPalCla(String uUPalCla) {
        this.uUPalCla = uUPalCla;
    }

    public String getUUAlt() {
        return uUAlt;
    }

    public void setUUAlt(String uUAlt) {
        this.uUAlt = uUAlt;
    }

    public String getUUHabDes() {
        return uUHabDes;
    }

    public void setUUHabDes(String uUHabDes) {
        this.uUHabDes = uUHabDes;
    }

    public Date getUUActQn() {
        return uUActQn;
    }

    public void setUUActQn(Date uUActQn) {
        this.uUActQn = uUActQn;
    }

    public String getUdescripciona() {
        return udescripciona;
    }

    public void setUdescripciona(String udescripciona) {
        this.udescripciona = udescripciona;
    }

    public String getUCuidados() {
        return uCuidados;
    }

    public void setUCuidados(String uCuidados) {
        this.uCuidados = uCuidados;
    }

    public Integer getUCojineria() {
        return uCojineria;
    }

    public void setUCojineria(Integer uCojineria) {
        this.uCojineria = uCojineria;
    }

    public String getUType() {
        return uType;
    }

    public void setUType(String uType) {
        this.uType = uType;
    }

    public BigDecimal getUNwrScrp() {
        return uNwrScrp;
    }

    public void setUNwrScrp(BigDecimal uNwrScrp) {
        this.uNwrScrp = uNwrScrp;
    }

    public Short getUNwrwopo() {
        return uNwrwopo;
    }

    public void setUNwrwopo(Short uNwrwopo) {
        this.uNwrwopo = uNwrwopo;
    }

    public Short getUNwrMatrix() {
        return uNwrMatrix;
    }

    public void setUNwrMatrix(Short uNwrMatrix) {
        this.uNwrMatrix = uNwrMatrix;
    }

    public Character getUNWRAutoShipment() {
        return uNWRAutoShipment;
    }

    public void setUNWRAutoShipment(Character uNWRAutoShipment) {
        this.uNWRAutoShipment = uNWRAutoShipment;
    }

    public String getUmateriales() {
        return umateriales;
    }

    public void setUmateriales(String umateriales) {
        this.umateriales = umateriales;
    }

    public String getUNumpartes() {
        return uNumpartes;
    }

    public void setUNumpartes(String uNumpartes) {
        this.uNumpartes = uNumpartes;
    }

    public String getUfotohd() {
        return ufotohd;
    }

    public void setUfotohd(String ufotohd) {
        this.ufotohd = ufotohd;
    }

    public String getUmodelo() {
        return umodelo;
    }

    public void setUmodelo(String umodelo) {
        this.umodelo = umodelo;
    }

    public Character getUescanearbot() {
        return uescanearbot;
    }

    public void setUescanearbot(Character uescanearbot) {
        this.uescanearbot = uescanearbot;
    }

    public String getUidyoutube() {
        return uidyoutube;
    }

    public void setUidyoutube(String uidyoutube) {
        this.uidyoutube = uidyoutube;
    }

    public String getUfotosOK() {
        return ufotosOK;
    }

    public void setUfotosOK(String ufotosOK) {
        this.ufotosOK = ufotosOK;
    }

    public String getUtalla() {
        return utalla;
    }

    public void setUtalla(String utalla) {
        this.utalla = utalla;
    }

    public String getUPosicionChaise() {
        return uPosicionChaise;
    }

    public void setUPosicionChaise(String uPosicionChaise) {
        this.uPosicionChaise = uPosicionChaise;
    }

    public String getUregistrocambio() {
        return uregistrocambio;
    }

    public void setUregistrocambio(String uregistrocambio) {
        this.uregistrocambio = uregistrocambio;
    }

    public String getUDescCorta() {
        return uDescCorta;
    }

    public void setUDescCorta(String uDescCorta) {
        this.uDescCorta = uDescCorta;
    }

    public String getUDescontinuado() {
        return uDescontinuado;
    }

    public void setUDescontinuado(String uDescontinuado) {
        this.uDescontinuado = uDescontinuado;
    }

    public String getUModelado() {
        return uModelado;
    }

    public void setUModelado(String uModelado) {
        this.uModelado = uModelado;
    }

    public Date getUreprocesarfotos() {
        return ureprocesarfotos;
    }

    public void setUreprocesarfotos(Date ureprocesarfotos) {
        this.ureprocesarfotos = ureprocesarfotos;
    }

    public String getuCodigoEan() {
        return uCodigoEan;
    }

    public void setuCodigoEan(String uCodigoEan) {
        this.uCodigoEan = uCodigoEan;
    }

    public String getuFactorRedondeo() {
        return uFactorRedondeo;
    }

    public void setuFactorRedondeo(String uFactorRedondeo) {
        this.uFactorRedondeo = uFactorRedondeo;
    }

    public String getuColeccion() {
        return uColeccion;
    }

    public void setuColeccion(String uColeccion) {
        this.uColeccion = uColeccion;
    }

    public String getuCodigoMarca() {
        return uCodigoMarca;
    }

    public void setuCodigoMarca(String uCodigoMarca) {
        this.uCodigoMarca = uCodigoMarca;
    }

    public String getuBloqueado() {
        return uBloqueado;
    }

    public void setuBloqueado(String uBloqueado) {
        this.uBloqueado = uBloqueado;
    }

    public String getuIdMercadoLibre() {
        return uIdMercadoLibre;
    }

    public void setuIdMercadoLibre(String uIdMercadoLibre) {
        this.uIdMercadoLibre = uIdMercadoLibre;
    }

    public String getuDescripcionML() {
        return uDescripcionML;
    }

    public void setuDescripcionML(String uDescripcionML) {
        this.uDescripcionML = uDescripcionML;
    }

    public String getuNombreMCL() {
        return uNombreMCL;
    }

    public void setuNombreMCL(String uNombreMCL) {
        this.uNombreMCL = uNombreMCL;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 59 * hash + Objects.hashCode(this.itemCode);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final ItemInventario other = (ItemInventario) obj;
        if (!Objects.equals(this.itemCode, other.itemCode)) {
            return false;
        }
        return true;
    }
}
